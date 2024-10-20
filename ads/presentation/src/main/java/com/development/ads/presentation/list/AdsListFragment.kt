package com.development.ads.presentation.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.ui.R
import com.development.ui.databinding.FragmentAdsListBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AdsListFragment : Fragment() {

    companion object {
        fun newInstance() = AdsListFragment()
    }

    private val viewModel: AdsListViewModel by activityViewModel()

    private lateinit var binding: FragmentAdsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            binding.progressIndicator.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
            if (uiState.adDataList.isNotEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.recyclerView.adapter = RecyclerViewAdapter(uiState.adDataList.map { it.addressInfo.address })
            }
            if (uiState.error != null) {
                binding.error.visibility = View.VISIBLE
                binding.error.text = uiState.error.toString()
            }
        })
    }
}