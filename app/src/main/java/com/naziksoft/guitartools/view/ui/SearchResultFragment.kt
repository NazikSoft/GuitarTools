package com.naziksoft.guitartools.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.view.adapters.SongsAdapter
import com.naziksoft.guitartools.databinding.FragmentSearchResultBinding
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.viewmodel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    private val request = "Marley"
    private lateinit var viewBinding: FragmentSearchResultBinding
    private val viewModel: SearchResultViewModel by viewModels()
    private val adapter = SongsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentSearchResultBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorator = DividerItemDecoration(requireContext(), VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_decorator)!!)

        with(viewBinding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(decorator)
            recyclerView.adapter = adapter
        }
        setupObservers()

        viewModel.search(request)
    }

    private fun setupObservers() {
        viewModel.getSongs().observe(viewLifecycleOwner, Observer<List<Song>> {
            adapter.songs = it
            adapter.notifyDataSetChanged()
        })
    }
}