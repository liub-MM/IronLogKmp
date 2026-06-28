package ironlogkmp.app.presentation.viewmodel

data class ProgressState(
    val totalWorkouts: Int = 0,
    val favoriteMuscle: String = "Немає даних",
    val totalTonnage: Int = 0,
    val isLoading: Boolean = true
)
