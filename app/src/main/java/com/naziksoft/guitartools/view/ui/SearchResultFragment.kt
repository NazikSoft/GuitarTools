package com.naziksoft.guitartools.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.view.adapters.SongsAdapter
import com.naziksoft.guitartools.databinding.FragmentSearchResultBinding
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.utils.Constants.EXTRA_SEARCHING_REQUEST
import com.naziksoft.guitartools.utils.Constants.EXTRA_SONGS_LIST
import com.naziksoft.guitartools.viewmodel.SearchResultViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment() {

    private val request by lazy { requireArguments().getString(EXTRA_SEARCHING_REQUEST) }
    private val songs by lazy { requireArguments().getSerializable(EXTRA_SONGS_LIST) as List<Song> }

    private lateinit var viewBinding: FragmentSearchResultBinding
    private val viewModel: SearchResultViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentSearchResultBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorator = DividerItemDecoration(requireContext(), VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_decorator)!!)

        with(viewBinding) {
            query.text = request

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(decorator)
                adapter = SongsAdapter(viewModel).also { it.setData(songs) }
            }
        }
    }
}