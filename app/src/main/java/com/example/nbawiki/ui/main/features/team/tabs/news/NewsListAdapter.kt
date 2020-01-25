package com.example.nbawiki.ui.main.features.team.tabs.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbawiki.databinding.ItemNewsLineBinding
import com.example.nbawiki.ui.main.features.team.models.NewsListElement

class NewsListAdapter(private var list: List<NewsListElement>, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsLineBinding.inflate(layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsListElement: NewsListElement = list[position]
        holder.bind(newsListElement)
    }

    override fun getItemCount(): Int = list.size

    fun update(newList: List<NewsListElement>) {
        list = newList
        this.notifyDataSetChanged()
    }

}
