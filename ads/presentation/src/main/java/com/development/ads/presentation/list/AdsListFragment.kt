package com.development.ads.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.ads.domain.model.AdData
import com.development.ads.presentation.detail.AdDetailFragment
import com.development.ui.databinding.FragmentAdsListBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AdsListFragment : Fragment() {

    companion object {
        fun newInstance() = AdsListFragment()
    }

    private val viewModel: AdsListViewModel by activityViewModel()

    private lateinit var binding: FragmentAdsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            binding.progressIndicator.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
            if (uiState.adDataList.isNotEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.recyclerView.adapter = RecyclerViewAdapter(
                    itemList = uiState.adDataList,
                    listener = object : OnItemClickListener {
                        override fun onItemClick(item: AdData) {
                            if (savedInstanceState == null) {
                                parentFragmentManager.beginTransaction()
                                    .replace(id, AdDetailFragment.newInstance())
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }
                    }
                )
            }
            if (uiState.error != null) {
                binding.error.visibility = View.VISIBLE
                binding.error.text = uiState.error.toString()
            }
        })
    }
}