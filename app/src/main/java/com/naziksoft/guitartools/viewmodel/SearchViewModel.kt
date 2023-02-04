package com.naziksoft.guitartools.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import com.naziksoft.guitartools.utils.Constants.EXTRA_SEARCHING_REQUEST
import com.naziksoft.guitartools.utils.Constants.EXTRA_SONGS_LIST

class SearchViewModel(private val repository: GuitarToolsRepository) : BaseViewModel() {

    private val foundedItems: MutableLiveData<List<Song>> by lazy { MutableLiveData<List<Song>>() }
    private var searchingRequest = ""

    fun onTextChanged(text: String) {
        searchingRequest = text
        if (text.isNotEmpty()) {
            runCoroutine { repository.search(text).onSuccess { foundedItems.postValue(it.body()) } }
        }
    }

    fun getFoundedItems(): LiveData<List<Song>> = foundedItems

    fun onButtonClicked(view: View) {
        if (searchingRequest.isNotBlank()) {
            view.findNavController().navigate(
                R.id.action_searchFragment_to_searchResultFragment,
                bundleOf(EXTRA_SONGS_LIST to foundedItems.value, EXTRA_SEARCHING_REQUEST to searchingRequest)
            )
        }
    }
}