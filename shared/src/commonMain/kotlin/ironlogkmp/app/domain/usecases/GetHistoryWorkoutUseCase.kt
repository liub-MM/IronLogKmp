package ironlogkmp.app.domain.usecases

import androidx.paging.PagingData
import ironlog.app.domain.model.Workout
import ironlogkmp.app.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryWorkoutUseCase(val repository: WorkoutRepository) {
    operator fun invoke(): Flow<PagingData<Workout>> {
        return repository.getWorkoutHistory()
    }
}