package com.development.ads.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}