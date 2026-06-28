package ironlogkmp.app.domain.usecases

import ironlogkmp.app.domain.repository.WorkoutRepository

class DeleteWorkoutUseCase (val repository: WorkoutRepository) {
    suspend operator fun invoke(workoutId: Long) {
        return repository.deleteWorkout(workoutId)
    }
}