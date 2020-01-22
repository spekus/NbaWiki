package com.example.nbawiki.ui.main.features.team.tabs.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemNewsLineBinding
import com.example.nbawiki.model.presentation.News

class NewsListAdapter(private var list: List<News>, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsLineBinding.inflate(layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news: News = list[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = list.size

    fun update(newList: List<News>) {
        list = newList
        this.notifyDataSetChanged()
    }

}
