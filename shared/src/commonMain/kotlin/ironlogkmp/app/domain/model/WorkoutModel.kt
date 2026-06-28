package ironlog.app.domain.model

data class Workout(
    val id: Long,
    val dateTimestamp: Long,
    val rawInputText: String,
    val exercises: List<Exercise>
)

data class Exercise(
    val id: Long,
    val name: String,
    val targetMuscle: String,
    val sets: List<Set>
)

data class Set(
    val id: Long,
    val weight: Float,
    val reps: Int,
    val setNumber: Int
)