package ar.com.albums.framework.retrofit.dto

data class PhotoDTO(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
