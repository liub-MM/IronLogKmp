package ironlogkmp.app

import androidx.compose.ui.window.ComposeUIViewController
import ironlogkmp.app.di.initKoin

fun MainViewController() = ComposeUIViewController { initKoin() }