package com.example.taskpost.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskpost.R
import com.example.taskpost.databinding.FragmentMeemsFeedBinding
import com.example.taskpost.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class meemsFeedFragment : Fragment(R.layout.fragment_meems_feed) {
    private var _binding: FragmentMeemsFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostViewModle by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMeemsFeedBinding.bind(view)
        setupViews()
    }

    fun setupViews() {
        val adapter = meemAdapter()

        binding.rvMeem.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.apply {
                        layoutError.visibility = View.VISIBLE
                        bpPosts.visibility = View.GONE
                        tvError.text = resource.message
                    }
                }
                is Resource.Success -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                        bpPosts.visibility = View.GONE
                    }
                    adapter.submitList(resource.data)
                }
                is Resource.Loading -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                        bpPosts.visibility = View.VISIBLE
                    }
                }
            }
        }
    }





    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}