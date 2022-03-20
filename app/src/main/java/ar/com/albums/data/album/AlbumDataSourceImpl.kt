package ar.com.albums.data.album

import ar.com.albums.framework.retrofit.dto.AlbumDTO
import ar.com.albums.framework.retrofit.service.AlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(private val service: AlbumService) : AlbumDataSource {

    private var listOfAlbumDTO: List<AlbumDTO> = listOf()

    override suspend fun getAlbums(): List<AlbumDTO> = withContext(Dispatchers.IO) {
        if (listOfAlbumDTO.size == 0) {
            service.getAlbums().await();
        } else {
            listOfAlbumDTO
        }
    }
}