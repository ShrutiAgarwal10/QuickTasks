package org.shruti.quicktasks.database

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepository(
    private val localDatabase: LocalDatabase
) {

    private val queries = localDatabase.provideQuickTasksDbQueries()

    // Get all tasks as Flow
    val allTasks: Flow<List<Tasks>> = queries.selectAll()
        .asFlow()
        .map { query ->
            query.executeAsList().map {
                Tasks(
                    id = it.id.toInt(),
                    content = it.content,
                    isCompleted = it.isCompleted.toInt() != 0
                )
            }
        }

    // Insert a new task
    suspend fun addTask(content: String, isCompleted: Boolean = false) {
        queries.insertTask(
            content = content,
            isCompleted = if (isCompleted) 1 else 0
        )
    }

    // Delete a task by ID
    suspend fun deleteTask(id: Int) {
        queries.deleteTask(id.toLong())
    }

    // Update completion status of task
    suspend fun updateCompleted(id: Int, isCompleted: Boolean) {
        queries.updateCompleted(
            isCompleted = if (isCompleted) 1 else 0,
            id = id.toLong()
        )
    }

}
