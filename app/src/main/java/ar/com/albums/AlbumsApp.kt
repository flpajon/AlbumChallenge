package ar.com.albums

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlbumsApp: Application(){
    companion object {
        const val TAG = "AlbumApp/"
    }
}