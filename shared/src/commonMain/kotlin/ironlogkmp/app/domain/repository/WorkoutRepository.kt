package ironlogkmp.app.domain.repository

import androidx.paging.PagingData
import ironlog.app.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {


    suspend fun syncWorkoutToCloud(workout: Workout): Result<Unit>

    fun getWorkoutById(id:Long): Flow<Workout?>
    suspend fun processAndSaveWorkout(rawText: String): Result<Workout>

    fun getWorkoutHistory(): Flow<PagingData<Workout>>

    fun getWorkoutsForAnalytics(minTimestamp: Long): Flow<List<Workout>>
    suspend fun deleteWorkout(workoutId: Long)
}