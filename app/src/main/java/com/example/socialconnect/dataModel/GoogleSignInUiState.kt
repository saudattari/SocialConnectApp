package com.example.socialconnect.dataModel

data class GoogleSignInUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val accountEmail: String? = null
)
