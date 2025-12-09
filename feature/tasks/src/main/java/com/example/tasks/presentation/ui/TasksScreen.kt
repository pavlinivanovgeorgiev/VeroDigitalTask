package com.example.tasks.presentation.ui

import android.graphics.Color
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasks.domain.Task
import com.example.tasks.presentation.TasksViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlin.jvm.java

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val uiState by remember {
        derivedStateOf { viewModel.uiState }
    }

    val qrLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result ->
        val contents = result.contents
        if (!contents.isNullOrEmpty()) {
            viewModel.onQrScanned(contents)
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Tasks") },
                actions = {
                    IconButton(
                        onClick = {
                            val options = ScanOptions().apply {
                                setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                                setPrompt("Scan QR code")
                                setBeepEnabled(true)
                                setBarcodeImageEnabled(false)
                                setCaptureActivity(PortraitCaptureActivity::class.java)
                                setOrientationLocked(true)
                            }
                            qrLauncher.launch(options)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Scan QR"
                        )
                    }
                }
            )
        }
    ) { padding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = uiState.isRefreshing),
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = viewModel::onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("Search…") },
                    singleLine = true
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.filteredTasks) { task ->
                        TaskListItem(task = task)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskListItem(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.task, style = MaterialTheme.typography.titleMedium)
                if (!task.title.isNullOrBlank()) {
                    Text(text = task.title, style = MaterialTheme.typography.bodyMedium)
                }
                if (!task.description.isNullOrBlank()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            ColorBox(colorCode = task.colorCode ?: "#808080")
        }
        Divider()
    }
}

@Composable
fun ColorBox(colorCode: String, modifier: Modifier = Modifier) {
    val safeColorInt = try {
        val c = colorCode?.trim()
        if (c.isNullOrEmpty()) {
            Color.parseColor("#FF9E9E9E")
        } else if (!c.startsWith("#")) {
            Color.parseColor("#$c")
        } else {
            Color.parseColor(c)
        }
    } catch (e: IllegalArgumentException) {
        Color.parseColor("#FF9E9E9E")
    }

    val color = androidx.compose.ui.graphics.Color(safeColorInt)

    Box(
        modifier = modifier
            .padding(start = 8.dp)
            .size(32.dp)
            .background(color, shape = MaterialTheme.shapes.small)
    )
}

