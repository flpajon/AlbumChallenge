package ar.com.albums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.albums.core.Result
import ar.com.albums.domain.model.Photo
import ar.com.albums.domain.GetAllPhotosByAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val useCaseGetAllPhotosByAlbum: GetAllPhotosByAlbum) :
    ViewModel() {

    private var _photoList = MutableLiveData<Result<List<Photo>>>(Result.Loading())

    var photoList: LiveData<Result<List<Photo>>> = _photoList

    fun getPhotoListByAlbum(albumId: Long) = viewModelScope.launch {
        kotlin.runCatching {
            useCaseGetAllPhotosByAlbum.getAllPhotoByAlbum(albumId)
        }.onSuccess { albumList ->
            this@PhotoViewModel._photoList.value = Result.Success(albumList)
        }.onFailure { throwable ->
            _photoList.value = Result.Failure(Exception(throwable))
        }
    }
}