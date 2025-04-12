/*
package org.shruti.quicktasks

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch

class TaskViewModel(private val db: TaskDatabaseHelper) {
    val tasks = db.getAllTasks().stateIn(
        CoroutineScope(Dispatchers.Default + SupervisorJob()),
        SharingStarted.Eagerly,
        emptyList()
    )

    fun addTask(name: String) {
        CoroutineScope(Dispatchers.Default).launch {
            db.insertTask(name)
        }
    }

    fun toggleTask(id: Long, currentState: Boolean) {
        CoroutineScope(Dispatchers.Default).launch {
            db.updateTaskCompletion(id, !currentState)
        }
    }

    fun deleteTask(id: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            db.deleteTask(id)
        }
    }
}
*/
