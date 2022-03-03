package com.naziksoft.guitartools.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naziksoft.guitartools.databinding.SongCardItemBinding
import com.naziksoft.guitartools.models.Song
import com.naziksoft.guitartools.models.TabTypes
import com.naziksoft.guitartools.utils.Extensions.makeVisibleOrGone

class SongsAdapter(var songs: List<Song> = listOf()) : RecyclerView.Adapter<SongsAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = SongCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount() = songs.size

    class SongViewHolder(private val viewHolder: SongCardItemBinding) : RecyclerView.ViewHolder(viewHolder.root) {
        fun bind(song: Song) {
            with(viewHolder) {
                artist.text = song.artist.name
                songName.text = song.title
                player.makeVisibleOrGone(song.tabTypes.contains(TabTypes.PLAYER))
                chords.makeVisibleOrGone(song.tabTypes.contains(TabTypes.CHORDS))
                guitar.makeVisibleOrGone(song.tabTypes.contains(TabTypes.TEXT_GUITAR_TAB))
                bass.makeVisibleOrGone(song.tabTypes.contains(TabTypes.TEXT_BASS_TAB))
            }
        }
    }
}
