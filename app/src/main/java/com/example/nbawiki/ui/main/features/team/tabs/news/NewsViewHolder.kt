package com.example.nbawiki.ui.main.features.team.tabs.news

import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemNewsLineBinding
import com.example.nbawiki.model.presentation.News

class NewsViewHolder(val binding : ItemNewsLineBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
    }
}