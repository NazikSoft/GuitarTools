package com.naziksoft.guitartools.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.utils.Constants.EXTRA_SONG

class SearchResultViewModel : BaseViewModel() {

    fun onItemClicked(view: View, song: Song) {
        view.findNavController().navigate(R.id.action_searchResultFragment_to_webViewFragment, bundleOf(EXTRA_SONG to song))
    }
}