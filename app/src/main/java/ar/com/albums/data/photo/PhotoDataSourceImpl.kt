package ar.com.albums.data.photo

import ar.com.albums.framework.retrofit.dto.PhotoDTO
import ar.com.albums.framework.retrofit.service.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

class PhotoDataSourceImpl @Inject constructor(private val service: PhotoService): PhotoDataSource {

    override suspend fun getPhotosByAlbum(albumId: Long): List<PhotoDTO> = withContext(Dispatchers.IO) {
        service.getPhotosByAlbumId(albumId).await()
    }
}