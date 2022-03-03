package com.naziksoft.guitartools.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.view.adapters.SongsAdapter
import com.naziksoft.guitartools.databinding.FragmentSearchResultBinding
import com.naziksoft.guitartools.repository.RemoteApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    @Inject
    lateinit var api: RemoteApi

    private val viewBinding: FragmentSearchResultBinding by lazy { FragmentSearchResultBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.search("Marley")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    with(viewBinding) {
                        val songs = response.body() ?: return@with
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        val decorator = DividerItemDecoration(requireContext(), VERTICAL)
                        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_decorator)!!)
                        recyclerView.addItemDecoration(decorator)
                        recyclerView.adapter = SongsAdapter(songs)
                    }
                } else {
                    Log.d("azaza", response.message())
                }
            }
        }
    }
}