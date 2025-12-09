package com.example.tasks.presentation

import com.example.tasks.domain.Task

data class TasksUiState(
    val isRefreshing: Boolean = false,
    val query: String = "",
    val allTasks: List<Task> = emptyList(),
    val filteredTasks: List<Task> = emptyList()
)
