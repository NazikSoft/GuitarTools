package com.naziksoft.guitartools.viewmodel

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import com.naziksoft.guitartools.utils.Constants.EXTRA_SEARCHING_REQUEST
import com.naziksoft.guitartools.utils.Constants.EXTRA_SONGS_LIST
import com.naziksoft.guitartools.utils.Constants.TAG
import com.naziksoft.guitartools.view.ui.SearchResultFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(private val repository: GuitarToolsRepository) : ViewModel() {

    private val foundedItems: MutableLiveData<List<Song>> by lazy { MutableLiveData<List<Song>>() }
    private var searchingRequest = ""

    fun onTextChanged(text: String) {




        searchingRequest = text
        if (text.isNotEmpty()) {
            viewModelScope.launch {
                repository.search(text).let { response ->
                    if (response.isSuccessful){
                        foundedItems.postValue(response.body())
                    } else {
                        Log.d(TAG, response.message())
                    }
                }
            }
        }
    }

    fun getFoundedItems(): LiveData<List<Song>> = foundedItems

    fun onButtonClicked(view: View) {
        if (searchingRequest.isNotBlank()){
            view.findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment,
                bundleOf(EXTRA_SONGS_LIST to foundedItems.value, EXTRA_SEARCHING_REQUEST to searchingRequest)
            )
        }
    }
}