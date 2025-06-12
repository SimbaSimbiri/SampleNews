package com.example.samplenews.android.screens

import android.net.Uri


enum class Screens(val route: String){
    ARTICLES("articles"),
    ARTICLE_DETAIL("article_detail/{url}"), // curly braces is how we indicate a named navArgument
    ABOUT_DEVICE("about_device"),
    SOURCES("sources");

    fun createDetailRoute(url: String) = "article_detail/${Uri.encode(url)}"
}