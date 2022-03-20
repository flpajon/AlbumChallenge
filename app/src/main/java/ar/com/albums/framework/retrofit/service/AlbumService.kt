package ar.com.albums.framework.retrofit.service

import ar.com.albums.framework.retrofit.dto.AlbumDTO
import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    fun getAlbums(): Call<List<AlbumDTO>>
}