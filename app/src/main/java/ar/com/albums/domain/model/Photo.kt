package ar.com.albums.domain.model

import ar.com.albums.framework.retrofit.dto.PhotoDTO

data class Photo(
    val id: Long,
    val title: String,
    val image: String
)

fun PhotoDTO.toPhoto(): Photo = Photo(
    this.id,
    this.title,
    this.thumbnailUrl
)