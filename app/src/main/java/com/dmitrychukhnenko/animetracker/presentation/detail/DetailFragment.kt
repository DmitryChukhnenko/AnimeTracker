package com.dmitrychukhnenko.animetracker.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dmitrychukhnenko.animetracker.R
import com.dmitrychukhnenko.animetracker.databinding.FragmentDetailBinding
import com.dmitrychukhnenko.animetracker.domain.model.Title
import org.koin.mp.KoinPlatform.getKoin

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleId = args.titleId
        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(titleId, getKoin().get())
        )[DetailViewModel::class.java]

        setupObservers()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        viewModel.titleDetails.observe(viewLifecycleOwner) { title ->
            title?.let { bindTitle(it) }
        }

//        viewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
//            binding..visibility = if (isLoading) View.VISIBLE else View.GONE
//        }

        binding.actionButton.setOnClickListener {
            viewModel.toggleSaveStatus()
        }
    }

    private fun bindTitle(title: Title) {
        with(binding) {
            toolbar.title = title.title
            titleText.text = title.title

            Glide.with(requireContext())
                .load(title.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(posterImage)

            synopsisText.text = title.synopsis ?: getString(R.string.no_synopsis)
            genresText.text = title.genres.joinToString(", ")

            scoreText.text = title.score?.let { "★ ${"%.1f".format(it)}" } ?: "★ N/A"

            actionButton.text = if (title.isSaved) {
                getString(R.string.action_remove)
            } else {
                getString(R.string.action_add)
            }

            actionButton.icon = ContextCompat.getDrawable(
                requireContext(),
                if (title.isSaved) R.drawable.ic_remove else R.drawable.ic_add
            )
        }
    }
}