package dev.ryan.feature.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.usecase.CreateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    fun onTitleChanged(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onDescriptionChanged(desc: String) {
        _uiState.update { it.copy(description = desc) }
    }

    fun onSaveTask() {
        viewModelScope.launch {
            val task = Task(
                id = 0,
                title = _uiState.value.title,
                description = _uiState.value.description,
                type = _uiState.value.type,
                date = _uiState.value.date,
                time = _uiState.value.time,
                status = TaskStatus.TODO
            )
            createTaskUseCase(task)
        }
    }
}
