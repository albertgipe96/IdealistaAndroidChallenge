package com.development.ui

import com.development.ads.domain.AdsRepository
import com.development.ads.domain.FavAdsRepository
import com.development.ads.domain.model.AdData
import com.development.ads.domain.model.AdImage
import com.development.ads.domain.model.AdSpecs
import com.development.ads.domain.model.FavoritedInfo
import com.development.ads.domain.model.OperationType
import com.development.ads.domain.model.PropertyCharacteristics
import com.development.ads.domain.model.PropertySpecs
import com.development.ads.domain.usecase.FetchAdDataDetailWithFavorite
import com.development.core.domain.result.DataError
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.mock
import kotlin.test.Test
import com.development.core.domain.result.Result
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.mockito.Mockito.`when`

class FetchAdDataDetailWithFavoriteTest {

    private lateinit var adsRepository: AdsRepository
    private lateinit var favAdsRepository: FavAdsRepository
    private lateinit var fetchAdDataDetailWithFavorite: FetchAdDataDetailWithFavorite

    private lateinit var fakeAdData: AdData

    @Before
    fun setUp() {
        adsRepository = mock(AdsRepository::class.java)
        favAdsRepository = mock(FavAdsRepository::class.java)
        fetchAdDataDetailWithFavorite = FetchAdDataDetailWithFavorite(adsRepository, favAdsRepository)
        fakeAdData = AdData(
            adId = 1,
            thumbnail = "url",
            adSpecs = AdSpecs(
                priceInfo = "1€",
                operation = OperationType.UNKNOWN
            ),
            propertySpecs = PropertySpecs(
                fullAddress = "address",
                municipality = "municipality",
                country = "country",
                latitude = 1L,
                longitude = 1L,
                characteristics = PropertyCharacteristics()
            ),
            images = emptyList<AdImage>()
        )
    }

    @Test
    fun `invoke should return success when ad details fetched successfully with no favorite info`() = runBlocking {
        // Given
        `when`(adsRepository.fetchAdDetail(fakeAdData.adId)).thenReturn(Result.Success(fakeAdData))
        `when`(favAdsRepository.getFavoritedInfo(fakeAdData.adId)).thenReturn(null)

        // When
        val result = fetchAdDataDetailWithFavorite(fakeAdData.adId)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(fakeAdData.favoritedInfo, (result as Result.Success).data.favoritedInfo)
    }

    @Test
    fun `invoke should return success when ad details fetched successfully with favorite info`() = runBlocking {
        // Given
        val favInfo = FavoritedInfo(fakeAdData.adId, true, 0)
        val adData = fakeAdData.copy(favoritedInfo = favInfo)
        `when`(adsRepository.fetchAdDetail(fakeAdData.adId)).thenReturn(Result.Success(adData))
        `when`(favAdsRepository.getFavoritedInfo(fakeAdData.adId)).thenReturn(favInfo)

        // When
        val result = fetchAdDataDetailWithFavorite.invoke(fakeAdData.adId)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(adData, (result as Result.Success).data)
    }

    @Test
    fun `should return error when adsRepository fetch fails`() = runBlocking {
        // Given
        `when`(adsRepository.fetchAdDetail(fakeAdData.adId)).thenReturn(Result.Error(DataError.Network.UNKNOWN))

        // When
        val result = fetchAdDataDetailWithFavorite(fakeAdData.adId)

        // Then
        assertTrue(result is Result.Error)
    }

}