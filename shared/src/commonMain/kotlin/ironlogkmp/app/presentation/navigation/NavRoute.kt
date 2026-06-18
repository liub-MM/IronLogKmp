package ironlogkmp.app.presentation.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Home : NavRoute()

    @Serializable
    object Login : NavRoute()

    @Serializable
    object Splash : NavRoute()

    @Serializable
    object Progress : NavRoute()

    @Serializable
    object History : NavRoute()

    @Serializable
    data class WorkoutDetails(val workoutId: Long) : NavRoute()
}