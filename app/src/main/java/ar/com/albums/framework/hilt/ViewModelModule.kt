package ar.com.albums.framework.hilt

import ar.com.albums.data.album.AlbumDataSourceImpl
import ar.com.albums.data.album.AlbumRepository
import ar.com.albums.data.photo.PhotoDataSourceImpl
import ar.com.albums.data.photo.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelModule {

    @Provides
    fun provideAlbumRepository(dataSource: AlbumDataSourceImpl): AlbumRepository =
        AlbumRepository(dataSource)

    @Provides
    fun providePhotoRepository(dataSource: PhotoDataSourceImpl): PhotoRepository =
        PhotoRepository(dataSource)
}