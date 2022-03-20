package ar.com.albums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.albums.core.Result
import ar.com.albums.domain.model.Album
import ar.com.albums.domain.GetAllAlbums
import ar.com.albums.domain.GetAllAlbumsByFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val useCaseGetAllAlbums: GetAllAlbums,
    private val useCaseGetAllAlbumsByFilter: GetAllAlbumsByFilter
) :
    ViewModel() {

    private var _albumList = MutableLiveData<Result<List<Album>>>(Result.Loading())

    var albumList: LiveData<Result<List<Album>>> = _albumList

    fun getAlbumList() = viewModelScope.launch {
        kotlin.runCatching {
            useCaseGetAllAlbums.getAllAlbums()
        }.onSuccess { albumList ->
            this@AlbumViewModel._albumList.value = Result.Success(albumList)
        }.onFailure { throwable ->
            _albumList.value = Result.Failure(Exception(throwable))
        }
    }

    fun doFilterAlbumList(filter: String) = viewModelScope.launch {
        kotlin.runCatching {
            _albumList.value = Result.Loading()
            useCaseGetAllAlbumsByFilter.getAllAlbumsByFilter(filter)
        }.onSuccess { albumList ->
            this@AlbumViewModel._albumList.value = Result.Success(albumList)
        }.onFailure { throwable ->
            _albumList.value = Result.Failure(Exception(throwable))
        }
    }
}