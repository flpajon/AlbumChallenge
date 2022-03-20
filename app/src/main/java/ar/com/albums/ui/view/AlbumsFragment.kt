package ar.com.albums.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ar.com.albums.AlbumsApp
import ar.com.albums.R
import ar.com.albums.core.AppConstants
import ar.com.albums.core.Result
import ar.com.albums.core.hide
import ar.com.albums.core.show
import ar.com.albums.domain.model.Album
import ar.com.albums.databinding.FragmentAlbumsBinding
import ar.com.albums.ui.adapter.AlbumAdapter
import ar.com.albums.ui.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(R.layout.fragment_albums), AlbumAdapter.OnAlbumClickListener {

    private val TAG: String = "${AlbumsApp.TAG}${this.javaClass.name}"

    lateinit var binding: FragmentAlbumsBinding

    private val viewModel by viewModels<AlbumViewModel>()

    private lateinit var rvAdapter: AlbumAdapter

    private val albumList = mutableListOf<Album>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)

        rvAdapter = AlbumAdapter(albumList, this)
        binding.rvAlbumList.adapter = rvAdapter

        loadAlbumList()

        getAlbumList()

        onSearchAlbum()
    }

    private fun getAlbumList() {
        viewModel.getAlbumList()
    }

    private fun onSearchAlbum() {
        binding.btnFilter.setOnClickListener {
            val filter = binding.etFiltro.text.toString()
            viewModel.doFilterAlbumList(filter)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadAlbumList() {
        viewModel.albumList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.tvListEmpty.hide()
                    binding.rvAlbumList.hide()
                    binding.pbAlbumList.show()
                }
                is Result.Success -> {
                    albumList.clear()
                    binding.pbAlbumList.hide()
                    if (result.data.size > 0) {
                        albumList.addAll(result.data)
                        rvAdapter.notifyDataSetChanged()
                        binding.tvListEmpty.hide()
                        binding.rvAlbumList.show()
                    } else {
                        binding.tvListEmpty.show()
                        binding.rvAlbumList.hide()
                    }
                    albumList.addAll(result.data)
                    rvAdapter.notifyDataSetChanged()
                    binding.rvAlbumList.show()
                }
                is Result.Failure -> {
                    binding.pbAlbumList.hide()
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

    override fun onAlbumClick(album: Album) {
        findNavController().navigate(
            R.id.action_albumsFragment_to_photoFragment,
            bundleOf(AppConstants.ALBUM_ID to album.id)
        )
    }

}