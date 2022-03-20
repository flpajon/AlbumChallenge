package ar.com.albums.data.photo

import ar.com.albums.framework.retrofit.dto.PhotoDTO

interface PhotoDataSource {

    suspend fun getPhotosByAlbum(albumId: Long): List<PhotoDTO>
}