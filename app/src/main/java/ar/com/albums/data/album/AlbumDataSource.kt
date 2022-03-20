package ar.com.albums.data.album

import ar.com.albums.framework.retrofit.dto.AlbumDTO

interface AlbumDataSource {
    suspend fun getAlbums():List<AlbumDTO>
}