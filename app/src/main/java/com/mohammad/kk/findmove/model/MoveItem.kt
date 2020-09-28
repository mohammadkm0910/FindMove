package com.mohammad.kk.findmove.model

class MoveItem(
    var picture: Int,
    var name: String,
    var director: String,
    var yearOfConstruction: String,
    var nameAuthor: String,
    var fullDescription: String,
) {
    var isExpanded = false
}