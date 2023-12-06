package com.example.myapplication

data class Fasilitas (
    val id : Int? = 0,
    val name: String? = "",
    val category: String? = "",
    val icon: String? = "",
    var isChecked: Boolean = false
)