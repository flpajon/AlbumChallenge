package ar.com.albums.domain

import ar.com.albums.data.album.AlbumRepository
import ar.com.albums.domain.model.Album
import javax.inject.Inject

class GetAllAlbumsByFilter @Inject constructor(private val repository: AlbumRepository) {

    suspend fun getAllAlbumsByFilter(filter: String): List<Album> =
        repository.getAlbumsByFilter(filter)
}