package ironlogkmp.app.domain.usecases

import ironlog.app.domain.model.Workout
import ironlogkmp.app.domain.repository.WorkoutRepository

class SyncWorkoutToCloudUseCase constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(workout: Workout): Result<Unit> {
        return repository.syncWorkoutToCloud(workout)
    }
}