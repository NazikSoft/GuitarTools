package com.naziksoft.guitartools.models

data class Song(
    val id: Int,
    val type: String,
    val title: String,
    val artist: Artist,
    val chordsPresent: Boolean,
    val tabTypes: List<TabTypes>
    )

enum class TabTypes{
    PLAYER,
    TEXT_GUITAR_TAB,
    CHORDS,
    TEXT_BASS_TAB
}