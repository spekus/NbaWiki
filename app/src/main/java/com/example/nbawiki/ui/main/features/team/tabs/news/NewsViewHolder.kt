package com.example.nbawiki.ui.main.features.team.tabs.news

import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.NewsLineItemBinding
import com.example.nbawiki.model.presentation.News

class NewsViewHolder(val binding : NewsLineItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
    }
}