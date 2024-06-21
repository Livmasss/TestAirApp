package com.livmas.search.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.GetFeedUseCase
import com.livmas.search.ui.home.music_adapter.FeedItemModel
import com.livmas.search.ui.mappers.SearchMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getFeedUseCase: GetFeedUseCase
): ViewModel() {
    val feed: LiveData<List<FeedItemModel>?>
        get() = _feed
    private val _feed: MutableLiveData<List<FeedItemModel>> by lazy {
        MutableLiveData(null)
    }

    fun refreshFeed() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getFeedUseCase.execute()
            _feed.postValue(result.map {
                SearchMapper.feedItemDTOToUiModel(it)
            })
        }
    }
}