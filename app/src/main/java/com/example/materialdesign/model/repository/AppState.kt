package com.example.materialdesign.model.repository

sealed class AppState {
    data class Success(val pdoServerResponse: PDOServerResponse) : AppState()
    data class Loading(val process: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}