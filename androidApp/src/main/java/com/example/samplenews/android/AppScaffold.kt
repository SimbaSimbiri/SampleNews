package com.example.samplenews.android

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samplenews.android.screens.AboutScreen
import com.example.samplenews.android.screens.ArticleDetailScreen
import com.example.samplenews.android.screens.ArticlesScreen
import com.example.samplenews.android.screens.Screens
import com.example.samplenews.android.screens.SourcesScreen
import com.example.samplenews.articles.application.Article


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val paddingValues = PaddingValues(all = 0.dp)

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screens.ARTICLES.route) { // if the current route is articles, navigate to ArticlesScreen
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
                onSourcesButtonClick = { navController.navigate(Screens.SOURCES.route) },
                onArticleClick = { article: Article ->
                    navController.navigate(Screens.ARTICLE_DETAIL.createDetailRoute(article.urlToPage))
                }

            )
        }

        composable(Screens.ARTICLE_DETAIL.route,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments!!.getString("url")!!
            ArticleDetailScreen(
                url = url,
                onBack = { navController.popBackStack() },
            )
        }

        composable(Screens.ABOUT_DEVICE.route) { // navigates to the AboutScreen
            AboutScreen(
                onBackButtonClick =  { navController.popBackStack() }
            )
        }

        composable(Screens.SOURCES.route) {
            SourcesScreen(
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    }
}