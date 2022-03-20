package ar.com.albums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.albums.core.BaseViewHolder
import ar.com.albums.domain.model.Album
import ar.com.albums.databinding.RvAlbumBinding

class AlbumAdapter(
    private val albumList: List<Album>,
    private val albumClickListener: OnAlbumClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnAlbumClickListener {
        fun onAlbumClick(album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RvAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AlbumListViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            albumClickListener.onAlbumClick(albumList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is AlbumListViewHolder -> holder.bind(albumList[position])
        }
    }

    override fun getItemCount(): Int = albumList.size

    private inner class AlbumListViewHolder(
        val binding: RvAlbumBinding
    ) : BaseViewHolder<Album>(binding.root) {
        override fun bind(item: Album) {
            binding.albumId.text = item.id.toString()
            binding.albumTitle.text = item.title.uppercase()
        }
    }
}