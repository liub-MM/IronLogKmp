package ironlogkmp.app.domain.usecases

import ironlog.app.domain.model.Workout
import ironlogkmp.app.domain.repository.WorkoutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ProcessAndSaveWorkoutUseCase (
    val repository: WorkoutRepository,
    private val syncToCloudUseCase: SyncWorkoutToCloudUseCase
) {
    suspend operator fun invoke(rawText: String): Result<Workout> {
        val result = repository.processAndSaveWorkout(rawText)

        result.onSuccess { savedWorkout ->
            CoroutineScope(Dispatchers.IO).launch {
                syncToCloudUseCase(savedWorkout)
            }
        }

        return result
    }
}