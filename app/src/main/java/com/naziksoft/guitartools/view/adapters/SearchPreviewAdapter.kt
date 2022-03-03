package com.naziksoft.guitartools.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naziksoft.guitartools.databinding.SearchPreviewCardItemBinding
import com.naziksoft.guitartools.models.Song

class SearchPreviewAdapter : RecyclerView.Adapter<SearchPreviewAdapter.SearchPreviewViewHolder>() {

    var items: List<Song> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPreviewViewHolder {
        val viewBinding = SearchPreviewCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchPreviewViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SearchPreviewViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class SearchPreviewViewHolder(private val viewBinding: SearchPreviewCardItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(song: Song) {
            with(viewBinding){
                artist.text = song.artist.name
                songName.text = song.title
            }
        }
    }
}