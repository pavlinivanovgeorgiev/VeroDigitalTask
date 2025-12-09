package com.example.tasks.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.collections.filter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.TasksRepository
import com.example.tasks.data.matchesQuery
import com.example.tasks.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel() {

    var uiState by mutableStateOf(TasksUiState())
        private set

    init {
        observeTasks()
        refresh()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            repository.observeTasks().collectLatest { tasks ->
                uiState = uiState.copy(
                    allTasks = tasks,
                    filteredTasks = applyFilter(tasks, uiState.query)
                )
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        uiState = uiState.copy(
            query = newQuery,
            filteredTasks = applyFilter(uiState.allTasks, newQuery)
        )
    }

    fun onQrScanned(text: String) {
        onQueryChange(text)
    }

    fun refresh() {
        viewModelScope.launch {
            uiState = uiState.copy(isRefreshing = true)
            try {
                repository.refreshTasks()
            } catch (_: Exception) {

            } finally {
                uiState = uiState.copy(isRefreshing = false)
            }
        }
    }

    private fun applyFilter(tasks: List<Task>, q: String) =
        tasks.filter {
            it.matchesQuery(q)
        }
}
