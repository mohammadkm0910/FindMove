package com.mohammad.kk.findmove.model

data class MovieItem(
    var picture: Int,
    var name: String,
    var director: String,
    var yearOfConstruction: String,
    var nameAuthor: String,
    var fullDescription: String,
) {
    var isExpanded = false
}