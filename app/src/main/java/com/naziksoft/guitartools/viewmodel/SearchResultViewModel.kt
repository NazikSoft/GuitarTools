package com.naziksoft.guitartools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import com.naziksoft.guitartools.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel
@Inject constructor(private val repository: GuitarToolsRepository) : ViewModel() {

    private val songs: MutableLiveData<List<Song>> by lazy { MutableLiveData<List<Song>>() }

    fun getSongs(): LiveData<List<Song>> = songs

    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query).let {response->
                if (response.isSuccessful) {
                    songs.postValue(response.body())
                } else {
                    Log.d(TAG, response.message())
                }
            }
        }
    }
}