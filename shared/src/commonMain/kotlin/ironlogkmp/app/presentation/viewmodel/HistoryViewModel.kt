package ironlogkmp.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ironlogkmp.app.domain.usecases.DeleteWorkoutUseCase
import ironlogkmp.app.domain.usecases.GetHistoryWorkoutUseCase
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getHistoryWorkoutUseCase: GetHistoryWorkoutUseCase
) : ViewModel() {

    val pagedWorkouts = getHistoryWorkoutUseCase()
        .cachedIn(viewModelScope)

    fun deleteWorkout(id: Long) {
        viewModelScope.launch {
            deleteWorkoutUseCase(id)
        }
    }
}