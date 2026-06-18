package ironlogkmp.app.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseWithSets(
    @Embedded val exercise: ExerciseEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val sets: List<SetEntity>
)

data class WorkoutWithDetails(
    @Embedded val workout: WorkoutEntity,

    @Relation(
        entity = ExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val exercises: List<ExerciseWithSets>
)