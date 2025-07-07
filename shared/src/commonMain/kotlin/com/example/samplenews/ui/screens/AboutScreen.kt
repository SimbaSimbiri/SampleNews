package com.example.samplenews.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.samplenews.Platform


@Composable
fun AboutScreen(
    ){

    Column{ // this is a precalculated list of a few items arranged vertically
        Toolbar("About Device")  // this is a self constructed composable, don't use the default ToolBar widget
        ContentView()
    }
}

@Composable
fun ContentView() {
    val deviceItems = makeItems()

    LazyColumn( // our lazyColum is similar to a RecyclerView:
        // It only shows the UI elements that are needed on screen. Otherwise they are deleted to
        // save on android resources
        modifier = Modifier.fillMaxSize()) {
            items(deviceItems){ deviceItem ->
                RowView(deviceItem.first, deviceItem.second)

            }

    }
    
}

@Composable
fun RowView(title: String, subtitle: String) {
    Column(Modifier.padding(8.dp)){
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Divider()
}

fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString())
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
     title: String

){
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

        }
    )

}