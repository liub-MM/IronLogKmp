package ironlogkmp.app.data.mappers

import ironlog.app.domain.model.Exercise
import ironlog.app.domain.model.Set
import ironlog.app.domain.model.Workout
import ironlogkmp.app.data.database.entity.ExerciseEntity
import ironlogkmp.app.data.database.entity.SetEntity
import ironlogkmp.app.data.database.entity.WorkoutWithDetails
import ironlogkmp.app.data.network.AiExercise
import ironlogkmp.app.data.network.AiSet
import ironlogkmp.app.data.network.AiWorkoutResponse
import ironlogkmp.app.utils.getCurrentSystemTime


//Dto Database
fun AiExercise.toDbModel(workoutId: Long = 0): ExerciseEntity {
    return ExerciseEntity(
        id = 0,
        workoutId = workoutId,
        name = this.name,
        targetMuscle = this.targetMuscle
    )
}


fun AiSet.toDbModel(exerciseId: Long = 0, setNumber: Int): SetEntity {
    return SetEntity(
        id = 0,
        exerciseId = exerciseId,
        weight = this.weight,
        reps = this.reps,
        setNumber = setNumber
    )
}

//Dto Domain
fun AiWorkoutResponse.toDomainModel(rawInput: String): Workout {
    return Workout(
        id = 0,
        dateTimestamp = getCurrentSystemTime(),
        rawInputText = rawInput,
        exercises = this.exercises.map { aiExercise ->
            Exercise(
                id = 0,
                name = aiExercise.name,
                targetMuscle = aiExercise.targetMuscle,
                sets = aiExercise.sets.mapIndexed { index, aiSet ->
                    Set(
                        id = 0,
                        weight = aiSet.weight,
                        reps = aiSet.reps,
                        setNumber = index + 1
                    )
                }
            )
        }
    )
}


//DB Domain
fun WorkoutWithDetails.toDomain(): Workout {
    return Workout(
        id = this.workout.id,
        dateTimestamp = this.workout.dateTimestamp,
        rawInputText = this.workout.rawInputText,
        exercises = this.exercises.map { relation ->
            Exercise(
                id = relation.exercise.id,
                name = relation.exercise.name,
                targetMuscle = relation.exercise.targetMuscle,
                sets = relation.sets.map { setEntity ->
                    Set(
                        id = setEntity.id,
                        weight = setEntity.weight,
                        reps = setEntity.reps,
                        setNumber = setEntity.setNumber
                    )
                }
            )
        }
    )

}