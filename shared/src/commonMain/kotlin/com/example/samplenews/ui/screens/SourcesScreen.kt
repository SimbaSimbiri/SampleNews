package com.example.samplenews.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.samplenews.sources.application.Source
import com.example.samplenews.sources.presentation.SourceState
import com.example.samplenews.sources.presentation.SourceViewModel
import com.example.samplenews.ui.screens.elements.ErrorMessage
import com.example.samplenews.ui.screens.elements.shimmerEffect
import com.example.samplenews.utils.openWebUrl
import org.koin.core.Koin


class SourcesScreen(val koin: Koin) : Screen {
    @Composable
    override fun Content() {
        SourcesScreenContent(koin)
    }
}
@Composable
fun SourcesScreenContent(
    koin: Koin,
    sourceViewModel: SourceViewModel = koin.get()
) {
    val sourceState by sourceViewModel.sourceStateFlow.collectAsState()

    Column {
        Toolbar("Sources")
        when (sourceState) {
            is SourceState.LoadingInitial -> ShimmerSourceList()
            is SourceState.Success -> {
                SourceListView(
                    sources = (sourceState as SourceState.Success).sources,
                    sourceViewModel
                )
            }

            is SourceState.Refreshing -> {
                SourceListView(
                    sources = (sourceState as SourceState.Refreshing).sources,
                    sourceViewModel
                )
            }

            is SourceState.Error -> ErrorMessage((sourceState as SourceState.Error).message)
            SourceState.Empty -> ErrorMessage("No sources found at the moment")
        }
    }

}

@Composable
fun ShimmerSourceList() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(5) {
            ShimmerSourceItemView()
        }
    }
}

@Composable
fun ShimmerSourceItemView() {
    val cardHeight = 200.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .height(cardHeight),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
                .background(MaterialTheme.colorScheme.surface)

        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SourceListView(
    sources: List<Source>,
    sourceViewModel: SourceViewModel
) {
    val state = rememberPullRefreshState(
        refreshing = (sourceViewModel.sourceStateFlow.value) is SourceState.Refreshing,
        onRefresh = {sourceViewModel.getSources(forceFetch = true)}
        )

    Box(modifier = Modifier.pullRefresh(state = state)){

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sources) { source ->
                SourceItemView(source = source)
            }
        }

        PullRefreshIndicator(refreshing = (sourceViewModel.sourceStateFlow.value) is SourceState.Refreshing,
            state = state, modifier = Modifier.align(Alignment.TopCenter))
    }

}

@Composable
fun SourceItemView(source: Source) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor   = MaterialTheme.colorScheme.onSurface
        )
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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {openWebUrl(source.homepage)}
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Open Homepage",
            modifier = Modifier.size(16.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${source.name} homepage",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}



