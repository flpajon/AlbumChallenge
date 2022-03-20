package ar.com.albums.domain

import ar.com.albums.data.photo.PhotoRepository
import javax.inject.Inject

class GetAllPhotosByAlbum @Inject constructor(private val repository: PhotoRepository) {

    suspend fun getAllPhotoByAlbum(albumId: Long) = repository.getPhotosByAlbum(albumId)
}