package org.shruti.quicktasks

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.shruti.quicktasks.database.Tasks
import org.shruti.quicktasks.database.TasksRepository

class TasksScreenViewModel(private val repository: TasksRepository) {

    private val viewModelScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main)

    val tasks: StateFlow<List<Tasks>> = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(content: String) {
        viewModelScope.launch {
            if (content.isNotBlank()) {
                repository.addTask(content)
            }
        }
    }

    fun toggleTaskCompleted(task: Tasks) {
        viewModelScope.launch {
            repository.updateCompleted(task.id, !task.isCompleted)
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            repository.deleteTask(id)
        }
    }
}
