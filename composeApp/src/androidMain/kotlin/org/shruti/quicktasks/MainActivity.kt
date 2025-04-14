package org.shruti.quicktasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.shruti.quicktasks.database.AndroidDatabaseDriverFactory
import org.shruti.quicktasks.database.LocalDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                LocalDatabase(
                    databaseDriverFactory = AndroidDatabaseDriverFactory(applicationContext)
                )
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(LocalDatabase(
        databaseDriverFactory = AndroidDatabaseDriverFactory(LocalContext.current)
    ))
}