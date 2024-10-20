package com.development.ads.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.model.AdData
import com.development.core.domain.result.DataError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.development.core.domain.result.Result

data class AdsListUiState(
    val isLoading: Boolean = false,
    val adDataList: List<AdData> = emptyList(),
    val error: DataError? = null
)

class AdsListViewModel(
    private val adsRepository: AdsRepository
) : ViewModel() {

    val uiState = MutableLiveData(AdsListUiState())

    init {
        fetchAdsList()
    }

    private fun fetchAdsList() {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            when (val result = adsRepository.fetchAdsData()) {
                is Result.Error -> uiState.postValue(AdsListUiState(isLoading = false, error = result.error))
                is Result.Success -> uiState.postValue(AdsListUiState(isLoading = false, adDataList = result.data))
            }
        } }
    }

}