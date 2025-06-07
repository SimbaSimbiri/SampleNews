package com.example.samplenews.android.screens

import android.net.Uri


enum class Screens(val route: String){
    ARTICLES("Isaac's articles"),
    ARTICLE_DETAIL("article_detail/{url}"),
    ABOUT_DEVICE("about-device"),
    SOURCES("Isaac's sources");

    fun createDetailRoute(url: String) = "article_detail/${Uri.encode(url)}"
}