package ar.com.albums.framework.retrofit.service

import ar.com.albums.framework.retrofit.dto.PhotoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("photos")
    fun getPhotosByAlbumId(
        @Query("albumId") albumId: Long
    ): Call<List<PhotoDTO>>
}