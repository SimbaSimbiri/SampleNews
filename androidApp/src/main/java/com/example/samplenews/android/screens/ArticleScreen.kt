package com.example.samplenews.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.samplenews.articles.Article
import com.example.samplenews.articles.ArticleState
import com.example.samplenews.articles.ArticlesViewModel

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit, articlesViewModel: ArticlesViewModel){
    // we want to collect/subscribe to the stream of info from the viewModel as an observable object

    val articleState = articlesViewModel.articleState.collectAsState()

    Column {
        AppBar(onAboutButtonClick)
        when(articleState.value){
            is ArticleState.Loading -> Loader()
            is ArticleState.Success -> ArticlesListView((articleState.value as ArticleState.Success).articles)
            is ArticleState.Error -> ErrorMessage((articleState.value as ArticleState.Error).message)
            is ArticleState.Empty -> ErrorMessage("No articles found today")
        }
    }

}

@Composable
fun ArticlesListView(articles: List<Article>) {
    LazyColumn (modifier = Modifier.fillMaxSize()){
        items(articles){ article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.title,
            style = TextStyle(fontSize = 22.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.description,
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))

    }
}

@Composable
fun ErrorMessage(error: String) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Text(
            text = error,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator(
            modifier = Modifier.width(50.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,

        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onAboutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text="Isaac's Articles", /*textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()*/)},
        actions = {

            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        }
    )
}
