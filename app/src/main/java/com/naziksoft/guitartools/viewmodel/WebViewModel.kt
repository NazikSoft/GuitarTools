package com.naziksoft.guitartools.viewmodel

import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebViewModel @Inject constructor(private val repository: GuitarToolsRepository) : ViewModel() {

    private val html: MediatorLiveData<String> by lazy { MediatorLiveData() }

    fun loadSong(song: Song) {
        viewModelScope.launch {
            repository.getSong(song.id).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()!!.string()
                    html.postValue(data)
                }
            }
        }
    }

    fun getHtml(): LiveData<String> = html
}