package ar.com.albums.data.album

import ar.com.albums.domain.model.Album
import ar.com.albums.domain.model.toAlbum
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val dataSource: AlbumDataSource) {

    suspend fun getAlbums(): List<Album> =
        dataSource.getAlbums().map { albumDTO -> albumDTO.toAlbum() }

    suspend fun getAlbumsByFilter(filter: String): List<Album> {
        return if (filter == "") {
            dataSource.getAlbums().map { albumDTO -> albumDTO.toAlbum() }
        } else {
            dataSource.getAlbums().filter { albumDTO -> filter in albumDTO.title }
                .map { albumDTO -> albumDTO.toAlbum() }
        }
    }

}