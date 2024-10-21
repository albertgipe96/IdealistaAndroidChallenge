package com.development.ads.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.model.AdData
import com.development.ads.domain.usecase.FetchAdDataDetailWithFavorite
import com.development.ads.domain.usecase.ToggleFavoriteAd
import com.development.ads.presentation.detail.AdDetailFragment.Companion.AD_ID_ARG
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class AdDetailUiState(
    val isLoading: Boolean = true,
    val adData: AdData? = null,
    val error: DataError? = null
)

sealed interface AddDetailUserAction {
    data class FavoriteButtonClick(val adId: Int, val isFavorited: Boolean) : AddDetailUserAction
}

class AdDetailViewModel(
    private val fetchAdDataDetailWithFavorite: FetchAdDataDetailWithFavorite,
    private val toggleFavoriteAd: ToggleFavoriteAd
) : ViewModel() {

    val uiState = MutableLiveData(AdDetailUiState())

    fun onAction(action: AddDetailUserAction) {
        when (action) {
            is AddDetailUserAction.FavoriteButtonClick -> handleFavButtonClick(action)
        }
    }

    private fun handleFavButtonClick(action: AddDetailUserAction.FavoriteButtonClick) {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            when (val result = toggleFavoriteAd(action.adId, action.isFavorited)) {
                is Result.Error -> uiState.postValue(AdDetailUiState(error = result.error))
                is Result.Success -> uiState.postValue(AdDetailUiState(adData = uiState.value?.adData?.copy(favoritedInfo = result.data)))
            }
        } }
    }

    fun fetchAdData(adId: Int) {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            when (val result = fetchAdDataDetailWithFavorite(adId)) {
                is Result.Error -> uiState.postValue(AdDetailUiState(isLoading = false, error = result.error))
                is Result.Success -> uiState.postValue(AdDetailUiState(isLoading = false, adData = result.data))
            }
        } }
    }

}