package dev.ryan.feature.addtask

data class AddTaskUiState(
    val title: String = "",
    val description: String = "",
    val type: String = "Work",
    val date: Long = System.currentTimeMillis(),
    val time: Long = System.currentTimeMillis(),
    val isSaving: Boolean = false,
    val error: String? = null
)
