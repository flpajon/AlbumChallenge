package ar.com.albums.data.photo

import ar.com.albums.domain.model.Photo
import ar.com.albums.domain.model.toPhoto
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val dataSource: PhotoDataSource) {

    suspend fun getPhotosByAlbum(albumId: Long): List<Photo> =
        dataSource.getPhotosByAlbum(albumId).map { photoDTO -> photoDTO.toPhoto() }
}