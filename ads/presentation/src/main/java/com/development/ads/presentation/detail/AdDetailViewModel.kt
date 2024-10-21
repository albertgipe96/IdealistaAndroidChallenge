package com.development.ads.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.model.AdData
import com.development.ads.domain.usecase.FetchAdDataDetailWithFavorite
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class AdDetailUiState(
    val isLoading: Boolean = false,
    val adData: AdData? = null,
    val error: DataError? = null
)

class AdDetailViewModel(
    private val fetchAdDataDetailWithFavorite: FetchAdDataDetailWithFavorite
) : ViewModel() {

    val uiState = MutableLiveData(AdDetailUiState())

    init {
        fetchAdData()
    }

    private fun fetchAdData() {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            when (val result = fetchAdDataDetailWithFavorite()) {
                is Result.Error -> uiState.postValue(AdDetailUiState(isLoading = false, error = result.error))
                is Result.Success -> uiState.postValue(AdDetailUiState(isLoading = false, adData = result.data))
            }
        } }
    }

}