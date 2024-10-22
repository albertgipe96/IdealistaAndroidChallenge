package com.development.ads.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.development.ads.domain.util.DateTimeConverter
import com.development.ui.R
import com.development.ui.databinding.FragmentAdDetailBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AdDetailFragment : Fragment() {

    companion object {
        const val AD_ID_ARG = "adIdArg"
        const val ADDRESS_ARG = "addressArg"
        fun newInstance(adId: Int, address: String): AdDetailFragment {
            return AdDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(AD_ID_ARG, adId)
                    putString(ADDRESS_ARG, address)
                }
            }
        }
    }

    private val viewModel: AdDetailViewModel by activityViewModel()

    private lateinit var binding: FragmentAdDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adId = arguments?.getInt(AD_ID_ARG) ?: -1 // Always not null
        viewModel.fetchAdData(adId)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.title = arguments?.getString(ADDRESS_ARG)
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            if (uiState.adData != null) {
                val images = uiState.adData.images.map { it.url }
                binding.viewPager.adapter = CarouselImageAdapter(images)
                binding.viewComposeDetail.setContent {
                    val adData = uiState.adData
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (adData.favoritedInfo.isFavorited && adData.favoritedInfo.date != null) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Magenta)
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = stringResource(R.string.favorited_on, DateTimeConverter.millisToDateString(adData.favoritedInfo.date!!, "dd/MM/yyyy hh:mm:ss")), color = Color.White)
                            }
                        }
                        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                            arguments?.getString(ADDRESS_ARG)?.let {
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = it,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = adData.adSpecs.priceInfo,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(16.dp))
                            adData.propertySpecs.characteristics.apply {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    val booleanCharacteristics = mapOf(
                                        hasAirConditioning to R.string.property_has_air_conditioning,
                                        hasBoxRoom to R.string.property_has_box_room,
                                        exterior to R.string.property_is_exterior
                                    )
                                    booleanCharacteristics.filter { it.key == true }.values.forEach {
                                        Text(
                                            modifier = Modifier
                                                .background(Color.Green.copy(alpha = 0.3F), RoundedCornerShape(6.dp))
                                                .padding(vertical = 2.dp, horizontal = 6.dp),
                                            text = stringResource(it)
                                        )
                                    }
                                    communityCosts?.let {
                                        Text(
                                            modifier = Modifier
                                                .background(Color.Green.copy(alpha = 0.3F), RoundedCornerShape(6.dp))
                                                .padding(vertical = 2.dp, horizontal = 6.dp),
                                            text = stringResource(R.string.property_community_costs, it.toString())
                                        )
                                    }
                                    roomNumber?.let {
                                        Text(
                                            modifier = Modifier
                                                .background(Color.Green.copy(alpha = 0.3F), RoundedCornerShape(6.dp))
                                                .padding(vertical = 2.dp, horizontal = 6.dp),
                                            text = stringResource(R.string.property_room_number, it.toString())
                                        )
                                    }
                                    bathNumber?.let {
                                        Text(
                                            modifier = Modifier
                                                .background(Color.Green.copy(alpha = 0.3F), RoundedCornerShape(6.dp))
                                                .padding(vertical = 2.dp, horizontal = 6.dp),
                                            text = stringResource(R.string.property_bath_number, it.toString())
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
                binding.favoriteButton.apply {
                    text = if (!uiState.adData.favoritedInfo.isFavorited) getString(R.string.add_favorite_button_title) else getString(R.string.remove_favorite_button_title)
                    setOnClickListener {
                        viewModel.onAction(
                            AddDetailUserAction.FavoriteButtonClick(
                                adId = adId,
                                isFavorited = uiState.adData.favoritedInfo.isFavorited
                            )
                        )
                    }
                }
            }
        })
    }
}