package com.dmitrychukhnenko.animetracker.presentation.search

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitrychukhnenko.animetracker.databinding.FragmentSearchBinding
import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.states.SearchState
import com.dmitrychukhnenko.animetracker.presentation.common.adapter.TitleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: TitleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = TitleAdapter { title ->
            navigateToDetail(title.id)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setupSearch() {
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }

        binding.searchContainer.setEndIconOnClickListener {
            binding.searchInput.text?.clear()
        }
    }

    private fun performSearch() {
        val query = binding.searchInput.text.toString().trim()
        if (query.isNotEmpty()) {
            hideKeyboard()
            viewModel.searchTitles(query)
        }
    }

    private fun setupObservers() {
        viewModel.searchState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchState.Loading -> showLoading(true)
                is SearchState.Success -> handleSuccess(state.data)
                is SearchState.Error -> showError(state.message)
            }
        }
    }

    private fun handleSuccess(data: List<Title>) {
        showLoading(false)
        if (data.isEmpty()) {
            showEmptyState(true)
        } else {
            showEmptyState(false)
            adapter.submitList(data)
        }
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.swipeRefresh.isRefreshing = show
    }

    private fun showEmptyState(show: Boolean) {
        binding.emptyText.visibility = if (show) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showError(message: String) {
        binding.errorText.text = message
        binding.errorText.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
    }

    private fun navigateToDetail(id: Int) {
        val action = SearchFragmentDirections.actionSearchToDetail(id)
        findNavController().navigate(action)
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}