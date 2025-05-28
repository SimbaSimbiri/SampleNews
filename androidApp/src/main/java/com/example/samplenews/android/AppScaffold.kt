package com.example.samplenews.android

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samplenews.android.screens.AboutScreen
import com.example.samplenews.android.screens.ArticlesScreen
import com.example.samplenews.android.screens.Screens
import com.example.samplenews.articles.ArticlesViewModel
import androidx.compose.ui.unit.dp



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(articlesViewModel: ArticlesViewModel) {
    val navController = rememberNavController()
    val paddingValues = PaddingValues(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 16.dp)

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            articlesViewModel = articlesViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    articlesViewModel: ArticlesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier.padding(0.dp),
    ) {
        composable(Screens.ARTICLES.route) { // if the current route is articles, navigate to ArticlesScreen
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
                articlesViewModel,
            )
        }

        composable(Screens.ABOUT_DEVICE.route) { // navigates to the AboutScreen
            AboutScreen(
                onBackButtonClick =  { navController.popBackStack() }
            )
        }
    }
}