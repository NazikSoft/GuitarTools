package com.naziksoft.guitartools.models

import android.os.Parcelable
import java.io.Serializable

data class Song(
    val id: Int,
    val type: String,
    val title: String,
    val artist: Artist,
    val chordsPresent: Boolean,
    val tabTypes: List<TabTypes>
    ) : Serializable

enum class TabTypes{
    PLAYER,
    TEXT_GUITAR_TAB,
    CHORDS,
    TEXT_BASS_TAB
}