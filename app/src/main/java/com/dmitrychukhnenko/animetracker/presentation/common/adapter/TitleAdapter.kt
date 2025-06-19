package com.dmitrychukhnenko.animetracker.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dmitrychukhnenko.animetracker.databinding.ItemTitleBinding
import com.dmitrychukhnenko.animetracker.domain.model.Title
import com.dmitrychukhnenko.animetracker.presentation.common.viewholder.TitleViewHolder

class TitleAdapter(
    private val onClick: (Title) -> Unit
) : ListAdapter<Title, TitleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val binding = ItemTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TitleViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Title>() {
        override fun areItemsTheSame(oldItem: Title, newItem: Title) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Title, newItem: Title) =
            oldItem == newItem
    }
}

