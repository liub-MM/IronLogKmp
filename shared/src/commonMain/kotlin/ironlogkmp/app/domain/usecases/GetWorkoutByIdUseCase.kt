package ironlogkmp.app.domain.usecases

import ironlog.app.domain.model.Workout
import ironlogkmp.app.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutByIdUseCase(
    private val repository: WorkoutRepository
) {
    operator fun invoke(id: Long): Flow<Workout?> {
        return repository.getWorkoutById(id)
    }
}