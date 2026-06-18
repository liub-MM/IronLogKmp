package ironlogkmp.app.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiWorkoutResponse(
    @SerialName("exercises") val exercises: List<AiExercise>
)

@Serializable
data class AiExercise(
    @SerialName("name") val name: String,
    @SerialName("targetMuscle") val targetMuscle: String,
    @SerialName("sets") val sets: List<AiSet>
)

@Serializable
data class AiSet(
    @SerialName("weight") val weight: Float,
    @SerialName("reps") val reps: Int
)