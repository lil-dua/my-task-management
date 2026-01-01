package dev.ryan.feature.home

data class HomeUiState(
    val userName: String = "User",
    val completedPercent: Int = 0,
    val taskTypes: Map<String, Int> = emptyMap(), // example: {"Work" -> 3, "Study" -> 2}
    val isLoading: Boolean = false,
    val error: String? = null
)