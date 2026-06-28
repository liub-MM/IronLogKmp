package ironlogkmp.app.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ironlogkmp.app.data.database.entity.ExerciseEntity
import ironlogkmp.app.data.database.entity.SetEntity
import ironlogkmp.app.data.database.entity.WorkoutEntity
import ironlogkmp.app.data.database.entity.WorkoutWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {


    @Transaction
    @Query("SELECT * FROM workouts ORDER BY dateTimestamp DESC")
    fun getPagedWorkoutsHistory(): PagingSource<Int, WorkoutWithDetails>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutById(id: Long): Flow<WorkoutWithDetails?>

    @Transaction
    @Query("SELECT * FROM workouts WHERE dateTimestamp >= :minDateTimestamp ORDER BY dateTimestamp ASC")
    fun getWorkoutsForAnalytics(minDateTimestamp: Long): Flow<List<WorkoutWithDetails>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: List<SetEntity>)

    @Query("DELETE FROM workouts WHERE id = :workoutId")
    suspend fun deleteWorkout(workoutId: Long)


    @Transaction
    suspend fun insertFullWorkout(
        workout: WorkoutEntity,
        exercisesWithSets: List<Pair<ExerciseEntity, List<SetEntity>>>
    ): Long {
        val workoutId = insertWorkout(workout)

        exercisesWithSets.forEach { (exercise, sets) ->
            val exerciseId = insertExercises(listOf(exercise.copy(workoutId = workoutId))).first()

            val setsWithIds = sets.map { it.copy(exerciseId = exerciseId) }
            insertSets(setsWithIds)
        }
        return workoutId
    }
}