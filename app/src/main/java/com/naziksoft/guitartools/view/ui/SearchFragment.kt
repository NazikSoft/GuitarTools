package com.naziksoft.guitartools.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.naziksoft.guitartools.R
import com.naziksoft.guitartools.databinding.SearchFragmentBinding
import com.naziksoft.guitartools.view.adapters.SearchPreviewAdapter
import com.naziksoft.guitartools.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var viewBinding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapter = SearchPreviewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = SearchFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_decorator)!!)

        with(viewBinding) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(decorator)
                adapter = this@SearchFragment.adapter
            }
            inputField.addTextChangedListener {
                viewModel.onTextChanged(it.toString())
            }
            button.setOnClickListener { viewModel.onButtonClicked(button) }
        }

        viewModel.getFoundedItems().observe(
            viewLifecycleOwner,
            {
                adapter.items = it
                adapter.notifyDataSetChanged()
            }
        )
    }
}