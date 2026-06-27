package ironlogkmp.app.data.network

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import dev.shreyaspatil.ai.client.generativeai.type.content
import dev.shreyaspatil.ai.client.generativeai.type.generationConfig
import ironlogkmp.app.shared.config.BuildKonfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class GeminiWorkoutParser {

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildKonfig.GEMINI_API_KEY,
        generationConfig = generationConfig {
            responseMimeType = "application/json"
        },
        systemInstruction = content {
            text(
                """
                Ти розумний фітнес-асистент. Твоє завдання - аналізувати текст користувача про його тренування і витягувати звідти дані.
                Користувач може писати сленгом (напр: "жим 100х8 3п"). Якщо вага вказана лише для першого підходу, продублюй її для інших.
                
                Ти ПОВИНЕН повернути валідний JSON у такій структурі:
                {
                  "exercises": [
                    {
                      "name": "Назва вправи",
                      "targetMuscle": "Група м'язів",
                      "sets": [ { "weight": 100.0, "reps": 8 } ]
                    }
                  ]
                }
                Ніколи не додавай ніякого тексту до або після JSON.
                """.trimIndent()
            )
        }
    )

    suspend fun parseWorkoutText(userInput: String): Result<AiWorkoutResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(userInput)
                val jsonText = response.text

                if (jsonText != null) {
                    val parsedData = jsonParser.decodeFromString<AiWorkoutResponse>(jsonText)
                    Result.success(parsedData)
                } else {
                    Result.failure(Exception("AI повернув порожню відповідь"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}