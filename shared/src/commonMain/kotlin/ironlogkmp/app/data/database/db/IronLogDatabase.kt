package ironlogkmp.app.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ironlogkmp.app.data.dao.WorkoutDao
import ironlogkmp.app.data.database.entity.ExerciseEntity
import ironlogkmp.app.data.database.entity.SetEntity
import ironlogkmp.app.data.database.entity.WorkoutEntity

@Database(
    version = 1,
    entities = [
        WorkoutEntity::class,
        SetEntity::class,
        ExerciseEntity::class,
    ],
    exportSchema = true
)
abstract class IronLogDatabase : RoomDatabase() {

    abstract val workoutDao: WorkoutDao
}