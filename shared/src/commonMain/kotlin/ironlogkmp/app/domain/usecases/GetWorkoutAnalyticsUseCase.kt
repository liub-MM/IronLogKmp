package ironlogkmp.app.domain.usecases

import ironlog.app.domain.model.Workout
import ironlogkmp.app.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutAnalyticsUseCase(val repository: WorkoutRepository) {
    operator fun invoke(minTimestamp: Long): Flow<List<Workout>> {
        return repository.getWorkoutsForAnalytics(minTimestamp)
    }
}