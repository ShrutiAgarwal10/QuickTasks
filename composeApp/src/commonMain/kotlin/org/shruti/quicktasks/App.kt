package org.shruti.quicktasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.shruti.quicktasks.database.LocalDatabase
import org.shruti.quicktasks.database.Tasks
import org.shruti.quicktasks.database.TasksRepository

@Composable
@Preview
fun App( db : LocalDatabase) {
    MaterialTheme {
        Column(
            Modifier.fillMaxSize()
                .padding(top = WindowInsets.statusBars
                .asPaddingValues()
                .calculateTopPadding()), // Respect status bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val tasksRepository = TasksRepository(localDatabase = db)
            val tasksScreenViewModel = TasksScreenViewModel(tasksRepository)
            MainScreen(viewModel = tasksScreenViewModel)

        }
    }
}

@Composable
fun MainScreen(viewModel: TasksScreenViewModel) {

    val taskList by viewModel.tasks.collectAsState()
    var newTaskContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add New Task Section
        Text(
            text = "Quick Tasks",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTaskContent,
                onValueChange = { newTaskContent = it },
                label = { Text("Enter a new task") },
                modifier = Modifier
                    .weight(1f)

            )

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    if (newTaskContent.isNotBlank()) {
                        viewModel.addTask(newTaskContent.trim())
                        newTaskContent = ""
                    }
                },
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Task List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = taskList.size) { task ->
                TaskItem(
                    task = taskList[task],
                    onToggleComplete = {
                        viewModel.toggleTaskCompleted(taskList[task]) },
                    onDelete = { viewModel.deleteTask(taskList[task].id) }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Tasks,
    onToggleComplete: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Checkbox(
                modifier = Modifier.align(Alignment.Top),
                checked = task.isCompleted,
                onCheckedChange = { onToggleComplete() },
            )

            Text(
                text = task.content,
                modifier = Modifier.weight(1f).padding(8.dp),
                style = MaterialTheme.typography.body1.copy(
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.isCompleted) Color.Gray else MaterialTheme.colors.onSurface
                )
            )

            IconButton(modifier = Modifier.align(Alignment.Top),
                onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}
