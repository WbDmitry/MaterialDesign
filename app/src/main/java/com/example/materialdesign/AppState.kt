package com.example.materialdesign

import com.example.materialdesign.repository.PDOServerResponse

sealed class AppState {
    data class Success(val pdoServerResponse: PDOServerResponse) : AppState()
    data class Loading(val process: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}