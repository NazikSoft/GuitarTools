package com.naziksoft.guitartools.models

data class Artist(
    val id: Int,
    val name: String,
    val nameWithoutThePrefix: String,
    val type: String,
    val useThePrefix: Boolean
)