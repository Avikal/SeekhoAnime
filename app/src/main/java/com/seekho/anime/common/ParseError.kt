package com.seekho.anime.common

fun parsePagingError(e: Throwable): String {
    return if (e.message?.contains("Unable to resolve host") == true)
        "NO_INTERNET"
    else
        "ERROR:${e.localizedMessage}"
}