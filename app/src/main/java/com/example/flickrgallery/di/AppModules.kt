package com.example.flickrgallery.di

import com.example.flickrgallery.network.FlickrApiService
import com.example.flickrgallery.repository.implementations.PhotosRepositoryImpl
import com.example.flickrgallery.repository.interfaces.PhotosRepository
import com.example.flickrgallery.viewmodel.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModules = module {
    single<FlickrApiService> { FlickrApiService() }

    single { Dispatchers.IO }

    single<PhotosRepository> {
        PhotosRepositoryImpl(
            flickrApiService = get(),
            dispatcher = get()
        )
    }

    single { PhotosViewModel(repository = get()) }
}
