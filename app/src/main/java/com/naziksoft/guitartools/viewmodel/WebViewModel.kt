package com.naziksoft.guitartools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import com.naziksoft.guitartools.utils.Dispatchers

class WebViewModel (private val repository: GuitarToolsRepository) : BaseViewModel() {

    private val html: MutableLiveData<String> by lazy { MutableLiveData() }

    fun loadSong(song: Song) {
        runCoroutine(Dispatchers.io()) { repository.getSong(song.id).onSuccess { html.postValue(it.body()!!.string()) } }
    }

    fun getHtml(): LiveData<String> = html
}