package dev.ryan.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.usecase.GetTasksForDateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTasksForDateUseCase: GetTasksForDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadTodayTasks(date: Long) {
        viewModelScope.launch {
            getTasksForDateUseCase(date).collect { tasks ->
                val completed = tasks.count { it.status == TaskStatus.DONE }
                val percent = if (tasks.isNotEmpty()) (completed * 100 / tasks.size) else 0
                val grouped = tasks.groupBy { it.type }.mapValues { it.value.size }

                _uiState.update {
                    it.copy(
                        completedPercent = percent,
                        taskTypes = grouped,
                        isLoading = false
                    )
                }
            }
        }
    }
}
