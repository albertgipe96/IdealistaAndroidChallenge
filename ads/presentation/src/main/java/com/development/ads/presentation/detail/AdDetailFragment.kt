package com.development.ads.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.development.ui.databinding.FragmentAdDetailBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AdDetailFragment : Fragment() {

    companion object {
        fun newInstance() = AdDetailFragment()
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
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            if (uiState.adData != null) {
                val images = uiState.adData.images.map { it.url }
                binding.viewPager.adapter = CarouselImageAdapter(images)
            }
        })

    }
}