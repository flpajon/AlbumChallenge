package ar.com.albums.domain

import ar.com.albums.data.album.AlbumRepository
import ar.com.albums.domain.model.Album
import javax.inject.Inject

class GetAllAlbums @Inject constructor(private val repository: AlbumRepository) {

    suspend fun getAllAlbums(): List<Album> = repository.getAlbums()
}