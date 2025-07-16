package com.example.samplenews.ui.screens
/*

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.samplenews.articles.application.Article
import com.example.samplenews.articles.presentation.ArticleState
import com.example.samplenews.articles.presentation.ArticlesViewModel
import com.example.samplenews.ui.screens.elements.ErrorMessage
import com.example.samplenews.ui.screens.elements.shimmerEffect
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel
import org.koin.compose.koinInject

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = koinInject(),
    // onArticleClick: (Article) -> Unit
) {
    // we want to collect/subscribe to the stream of info from the viewModel as an observable object
    val articleState by articlesViewModel.articleStateFlow.collectAsState()

    Column {
        AppBar("Articles")
        when (articleState) {
            is ArticleState.LoadingInitial -> ShimmerList() // show shimmer UI
            is ArticleState.Success -> ArticlesListView(
                (articleState as ArticleState.Success).articles,
                false, onArticleClick,
                onRefresh = { articlesViewModel.getArticles(forceFetch = true) }
            )

            is ArticleState.Refreshing -> ArticlesListView(
                (articleState as ArticleState.Refreshing).articles,
                true,
                onArticleClick,
                onRefresh = { articlesViewModel.getArticles(forceFetch = true) }
            )

            is ArticleState.Error -> ErrorMessage((articleState as ArticleState.Error).message)
            is ArticleState.Empty -> ErrorMessage("No articles found")
        }

    }

}

@Composable
fun ArticlesListView(
    articles: List<Article>,
    isRefreshing: Boolean,
    onArticleClick: (Article) -> Unit,
    onRefresh: () -> Unit,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = onRefresh
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) { article ->
                ArticleItemView(article, onItemClick = { onArticleClick(article) })
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article, onItemClick: () -> Unit) {
    val imageHeight = imageHeight()

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
                .clickable { onItemClick() }

        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = article.description,
                style = TextStyle(fontSize = 14.sp),
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.publisher,
                    style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                )

                Text(
                    text = article.date,
                    style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                )
            }

        }
    }
}

@Composable
fun imageHeight(): Dp {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val imageHeight = screenHeight * 0.30f
    return imageHeight
}

@Composable
fun ShimmerList() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(5) {
            ShimmerItemView()
        }
    }
}

@Composable
fun ShimmerItemView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .height(250.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),


        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
                .background(MaterialTheme.colorScheme.surfaceVariant)

        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.List,
                    contentDescription = "Sources Page",
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Page",
                )
            }
        }
    )
}
*/
