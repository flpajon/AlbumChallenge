package ar.com.albums.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import ar.com.albums.AlbumsApp
import ar.com.albums.R
import ar.com.albums.core.AppConstants
import ar.com.albums.core.Result
import ar.com.albums.core.hide
import ar.com.albums.core.show
import ar.com.albums.domain.model.Photo
import ar.com.albums.databinding.FragmentPhotoBinding
import ar.com.albums.ui.adapter.PhotoAdapter
import ar.com.albums.ui.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private val TAG: String = "${AlbumsApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentPhotoBinding

    private val viewModel by viewModels<PhotoViewModel>()

    private lateinit var rvAdapter: PhotoAdapter

    private val photoList = mutableListOf<Photo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoBinding.bind(view)

        rvAdapter = PhotoAdapter(photoList)
        binding.rvPhotoList.adapter = rvAdapter

        loadPhotoList()

        getPhotoList()
    }

    private fun getPhotoList() {
        arguments?.let { bundle ->
            val albumId = bundle.getLong(AppConstants.ALBUM_ID)
            viewModel.getPhotoListByAlbum(albumId)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadPhotoList() {
        viewModel.photoList.observe(viewLifecycleOwner){ result ->
            when (result) {
                is Result.Loading -> {
                    binding.tvListEmpty.hide()
                    binding.rvPhotoList.hide()
                    binding.pbPhotoList.show()
                }
                is Result.Success -> {
                    photoList.clear()
                    binding.pbPhotoList.hide()
                    if (result.data.size > 0){
                        photoList.addAll(result.data)
                        rvAdapter.notifyDataSetChanged()
                        binding.tvListEmpty.hide()
                        binding.rvPhotoList.show()
                    }else{
                        binding.tvListEmpty.show()
                        binding.rvPhotoList.hide()
                    }
                }
                is Result.Failure -> {
                    binding.pbPhotoList.hide()
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un problema",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, result.exception.toString())
                }
            }
        }
    }
}