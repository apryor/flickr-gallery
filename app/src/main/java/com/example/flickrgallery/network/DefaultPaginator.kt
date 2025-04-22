package com.example.flickrgallery.network

class DefaultPaginator<Key, Item>(
    // initial value when paginator is first created
    private val initialKey: Key,
    // lambda functions for callbacks
    private val onLoadUpdated: (Boolean) -> Unit, // called when loading value is updated
    private val onRequest: suspend (nextPage: Key) -> NetworkResult<List<Item>>, // called when next page is requested
    private val getNextKey: suspend (List<Item>) -> Key, // called when next page key is needed
    private val onError: suspend (Throwable?) -> Unit, // called when error occurs
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit // called when successful
) : Paginator<Key, Item> {

    private var currentKey: Key = initialKey
    private var isMakingRequest = false // prevents overlapping calls

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        val items = when (result) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> {
                onError(Throwable(result.message))
                onLoadUpdated(false)
                return
            }
        }

        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}
