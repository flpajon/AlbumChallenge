package ar.com.albums.domain.model

import ar.com.albums.framework.retrofit.dto.AlbumDTO

data class Album(
    val id: Long,
    val title: String
)

fun AlbumDTO.toAlbum(): Album = Album(
    this.id,
    this.title
)