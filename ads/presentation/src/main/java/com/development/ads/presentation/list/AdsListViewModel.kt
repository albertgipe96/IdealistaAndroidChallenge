package com.development.ads.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.model.AdData
import com.development.ads.domain.usecase.FetchAdDataListWithFavorites
import com.development.core.domain.result.DataError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.development.core.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher

data class AdsListUiState(
    val isLoading: Boolean = false,
    val adDataList: List<AdData> = emptyList(),
    val error: DataError? = null
)

class AdsListViewModel(
    private val fetchAdDataListWithFavorites: FetchAdDataListWithFavorites,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState = MutableLiveData(AdsListUiState())

    init {
        fetchAdsList()
    }

    private fun fetchAdsList() {
        viewModelScope.launch { withContext(ioDispatcher) {
            when (val result = fetchAdDataListWithFavorites()) {
                is Result.Error -> uiState.postValue(AdsListUiState(isLoading = false, error = result.error))
                is Result.Success -> uiState.postValue(AdsListUiState(isLoading = false, adDataList = result.data))
            }
        } }
    }

}