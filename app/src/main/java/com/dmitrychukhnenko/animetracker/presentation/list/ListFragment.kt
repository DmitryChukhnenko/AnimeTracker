package com.dmitrychukhnenko.animetracker.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.bundle.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitrychukhnenko.animetracker.databinding.FragmentListBinding
import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.domain.states.ListState
import com.dmitrychukhnenko.animetracker.presentation.common.adapter.TitleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModel()
    private lateinit var adapter: TitleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupRefresh()
    }

    private fun setupRecyclerView() {
        adapter = TitleAdapter { title ->
            navigateToDetail(title.id)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ListFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setupObservers() {
        viewModel.listState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListState.Loading -> showLoading(true)
                is ListState.Success -> handleSuccess(state.data)
                is ListState.Error -> showError(state.message)
                is ListState.Empty -> showEmptyState(true)
            }
        }
    }

    private fun handleSuccess(data: List<Title>) {
        showLoading(false)
        showEmptyState(data.isEmpty())
        adapter.submitList(data)
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadTitles()
        }
    }

    private fun navigateToDetail(id: Int) {
        val action = ListFragmentDirections.actionListToDetail(id)
        findNavController().navigate(action)
    }
}