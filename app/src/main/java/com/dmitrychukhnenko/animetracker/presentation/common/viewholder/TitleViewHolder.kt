package com.dmitrychukhnenko.animetracker.presentation.common.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmitrychukhnenko.animetracker.R
import com.dmitrychukhnenko.animetracker.databinding.ItemTitleBinding
import com.dmitrychukhnenko.animetracker.domain.model.Title

class TitleViewHolder(
    private val binding: ItemTitleBinding,
    private val onClick: (Title) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(title: Title) {
        with(binding) {
            titleText.text = title.title

            scoreText.text = title.score?.let { "★ ${"%.1f".format(it)}" } ?: "★ N/A"

            genresText.text = title.genres.take(2).joinToString(", ")

            Glide.with(itemView.context)
                .load(title.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(posterImage)

            root.setOnClickListener { onClick(title) }
        }
    }
}