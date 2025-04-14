package org.shruti.quicktasks

import androidx.compose.ui.window.ComposeUIViewController
import org.shruti.quicktasks.database.IOSDatabaseDriverFactory
import org.shruti.quicktasks.database.LocalDatabase

fun MainViewController() = ComposeUIViewController { App(
    LocalDatabase(
        databaseDriverFactory = IOSDatabaseDriverFactory()
    )
) }