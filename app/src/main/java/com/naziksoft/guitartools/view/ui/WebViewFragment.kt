package com.naziksoft.guitartools.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naziksoft.guitartools.databinding.FragmentWebViewBinding
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.utils.Constants.BASE_URL
import com.naziksoft.guitartools.utils.Constants.EXTRA_SONG
import com.naziksoft.guitartools.viewmodel.WebViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewFragment : Fragment() {

    private lateinit var viewBinding: FragmentWebViewBinding
    private val viewModel: WebViewModel by viewModel()
    private val song by lazy { requireArguments().getSerializable(EXTRA_SONG) as Song }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentWebViewBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.webView.settings.apply {
            javaScriptEnabled = true
        }

        viewModel.getHtml().observe(viewLifecycleOwner, { html ->
            viewBinding.webView.loadDataWithBaseURL(BASE_URL, html, "text/html", "en-us", null)
        })

        viewModel.loadSong(song)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding.webView.destroy()
    }
}