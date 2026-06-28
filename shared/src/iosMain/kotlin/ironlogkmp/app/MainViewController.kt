package ironlogkmp.app

import androidx.compose.ui.window.ComposeUIViewController
import ironlogkmp.app.di.initKoin
import ironlogkmp.app.presentation.navigation.AppNavHost
import platform.UIKit.UIViewController

private var isKoinInitialized = false

fun MainViewController(): UIViewController {
    if (!isKoinInitialized) {
        initKoin()
        isKoinInitialized = true
    }

    return ComposeUIViewController {
        AppNavHost()
    }
}

