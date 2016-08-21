package com.example.app.presentation.di.modules

import com.example.app.presentation.utils.imageloader.FrescoImageLoader
import com.example.app.presentation.utils.imageloader.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides all necessary at presentation level
 */
@Module
class PresentationModule {

    @Provides
    @Singleton
    internal fun provideImageLoader(frescoImageLoader: FrescoImageLoader): ImageLoader {
        return frescoImageLoader
    }
}
