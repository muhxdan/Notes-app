package com.salt.apps.notes.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.notes.presentation.screens.main.MainScreen
import com.salt.apps.notes.presentation.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()
        setContent {
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    private fun setupSplashScreen() {
        var keepSplashOnScreen = true
        val delay = 1000L

        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({ keepSplashOnScreen = false }, delay)
    }
}