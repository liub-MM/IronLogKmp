package ironlogkmp.app.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual fun getCurrentSystemTime(): Long = System.currentTimeMillis()
actual fun formatWorkoutDate(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("uk", "UA"))
    return dateFormat.format(Date(timestamp))
}