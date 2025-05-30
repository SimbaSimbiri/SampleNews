package com.example.samplenews.android.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.samplenews.sources.application.Source
import com.example.samplenews.sources.presentation.SourceState
import com.example.samplenews.sources.presentation.SourceViewModel
import org.koin.androidx.compose.getViewModel
import androidx.core.net.toUri

@Composable
fun SourcesScreen(
    sourceViewModel: SourceViewModel = getViewModel<SourceViewModel>(),
    onBackButtonClick: () -> Unit
) {
    val sourceState = sourceViewModel.sourceStateFlow.collectAsState()

    Column {
        Toolbar(onBackButtonClick, "Isaac's Sources")
        when (sourceState.value) {
            is SourceState.Loading -> Loader()
            is SourceState.Success -> SourceListView((sourceState.value as SourceState.Success).sources)
            is SourceState.Error -> ErrorMessage((sourceState.value as SourceState.Error).message)
            else -> ErrorMessage("No sources found")
        }
    }

}

@Composable
fun SourceListView(sources: List<Source>) {
    LazyColumn (modifier = Modifier.fillMaxSize()){
        items(sources) { source ->
            SourceItemView(source = source)
        }
    }

}

@Composable
fun SourceItemView(source: Source) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = source.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = source.description,
                style = TextStyle(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Text(
                    text = source.homepage,
                    style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                )*/

                HomepageText(source)

                Text(
                    text = source.origin,
                    style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                )
            }

        }
    }
}

@Composable
private fun HomepageText(source: Source) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, source.homepage.toUri())
            ContextCompat.startActivity(context, intent, null)
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Home, // or Icons.Outlined.Link
            contentDescription = "Open Homepage",
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${source.name} homepage",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}
