package ar.com.albums.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.albums.core.BaseViewHolder
import ar.com.albums.domain.model.Photo
import ar.com.albums.databinding.RvPhotoBinding
import com.bumptech.glide.Glide

class PhotoAdapter(private val photoList: List<Photo>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = RvPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PhotoViewHolder ->holder.bind(photoList[position])
        }
    }

    override fun getItemCount(): Int = photoList.size

    private inner class PhotoViewHolder(
        val binding: RvPhotoBinding,
        val context: Context
    ) : BaseViewHolder<Photo>(binding.root) {
        override fun bind(item: Photo) {
            binding.photoId.text = item.id.toString()
            binding.photoTitle.text = item.title.uppercase()
            Log.e("PHOTO IMAGE URL", item.image)
            Glide.with(context).load(item.image).centerCrop().into(binding.photoImage)
        }

    }
}