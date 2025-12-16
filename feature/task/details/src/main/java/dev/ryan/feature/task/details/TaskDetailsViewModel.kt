package dev.ryan.feature.task.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.usecase.DeleteTaskUseCase
import dev.ryan.core.domain.usecase.UpdateTaskStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val updateTaskStatusUseCase: UpdateTaskStatusUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskDetailsUiState())
    val uiState: StateFlow<TaskDetailsUiState> = _uiState.asStateFlow()

    fun loadTask(task: Task) {
        _uiState.update { it.copy(task = task) }
    }

    fun updateStatus(status: TaskStatus) {
        _uiState.value.task?.let {
            viewModelScope.launch {
                updateTaskStatusUseCase(it.id, status)
                _uiState.update { state -> state.copy(task = it.copy(status = status)) }
            }
        }
    }

    fun deleteTask() {
        _uiState.value.task?.let {
            viewModelScope.launch {
                deleteTaskUseCase(it.id)
            }
        }
    }
}
