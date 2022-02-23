package com.example.materialdesign.ui

import android.app.Application

class App : Application() {
    companion object {
        const val APP_PREF_NAME = "AppSettings"
        const val PREF_THEME_KEY = "AppTheme"
    }
}