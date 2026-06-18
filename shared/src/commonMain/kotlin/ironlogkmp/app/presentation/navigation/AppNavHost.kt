package ironlogkmp.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ironlogkmp.app.presentation.screen.HistoryScreen
import ironlogkmp.app.presentation.screen.HomeScreen
import ironlogkmp.app.presentation.screen.ProgressScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            IronLogBottomNavigation(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoute.Home,
            modifier = modifier.padding(innerPadding),
        ) {
            composable<NavRoute.Home> {
                HomeScreen {
                    navController.navigate(NavRoute.History)
                }
            }

            composable<NavRoute.History> {
                HistoryScreen {
                    navController.navigate(NavRoute.Progress)

                }
            }

            composable<NavRoute.Progress> {
                ProgressScreen {}
            }
        }
    }
}


@Composable
fun IronLogBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NavigationBarItem(
            selected = currentDestination?.hasRoute<NavRoute.Home>() == true,
            onClick = { navigateBottomBar(navController, NavRoute.Home) },
            icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Головна") },
            label = { Text("Тренування") }
        )

        NavigationBarItem(
            selected = currentDestination?.hasRoute<NavRoute.History>() == true,
            onClick = { navigateBottomBar(navController, NavRoute.History) },
            icon = { Icon(Icons.Default.History, contentDescription = "Історія") },
            label = { Text("Історія") }
        )

        NavigationBarItem(
            selected = currentDestination?.hasRoute<NavRoute.Progress>() == true,
            onClick = { navigateBottomBar(navController, NavRoute.Progress) },
            icon = { Icon(Icons.Default.Analytics, contentDescription = "Прогрес") },
            label = { Text("Прогрес") }
        )
    }
}

private fun navigateBottomBar(navController: NavHostController, route: Any) {
    navController.navigate(route) {
        popUpTo(NavRoute.Home) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}