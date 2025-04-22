package com.example.flickrgallery.network

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
