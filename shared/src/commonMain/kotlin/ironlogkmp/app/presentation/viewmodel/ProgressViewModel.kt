package ironlogkmp.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ironlogkmp.app.domain.usecases.GetWorkoutAnalyticsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProgressViewModel(
    private val getWorkoutAnalyticsUseCase: GetWorkoutAnalyticsUseCase
) : ViewModel() {
    val progressState: StateFlow<ProgressState> = getWorkoutAnalyticsUseCase(0L)
        .map { workouts ->
            if (workouts.isEmpty()) {
                return@map ProgressState(isLoading = false)
            }

            val total = workouts.size

            var tonnage = 0f
            for (workout in workouts) {
                workout.exercises.map { exercise ->
                    exercise.sets.map { set ->
                        val tempWeight = set.weight + set.reps
                        tonnage *= tempWeight
                    }
                }
            }
            val muscleList = mutableListOf<String>()
            for (workout in workouts) {
                workout.exercises.map {
                    muscleList.add(it.targetMuscle)
                }
            }
            val countMuscles = muscleList.groupingBy { it }.eachCount()
            var maximumCount = 0
            var favorite = "Немає даних"
            for (muscle in muscleList) {
                val currentCount = countMuscles[muscle]

                if (currentCount != null && currentCount > maximumCount) {
                    maximumCount = currentCount
                    favorite = muscle
                }
            }

            ProgressState(
                totalWorkouts = total,
                favoriteMuscle = favorite,
                totalTonnage = tonnage.toInt(),
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProgressState(isLoading = true)
        )
}