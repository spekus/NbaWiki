package com.example.nbawiki.ui.main.features.team.tabs.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbawiki.MyApplication
import com.example.nbawiki.R
import com.example.nbawiki.dagger.CustomViewModelFactory
import com.example.nbawiki.databinding.FragmentListBinding
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class NewsListFragment : DaggerFragment() {
    @Inject
    lateinit var daggerFactory: CustomViewModelFactory


    lateinit var viewModel: NewsListViewModel
    lateinit var binding: FragmentListBinding

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

    init {
//        MyApplication.get().component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRecyclerViewAdapter()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, daggerFactory).get(NewsListViewModel::class.java)
//        viewModel = ViewModelProviders.of(this, ViewModelFactory { TeamViewModel(teamRepository) })
//            .get(TeamViewModel::class.java)
    }

    private fun setUpRecyclerViewAdapter() {
        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NewsListAdapter(viewModel.newsListElement.value ?: listOf(), layoutInflater)
        }

        viewModel.newsListElement.observe(viewLifecycleOwner, Observer<List<NewsListElement>> {
            val adapter = team_recycler_view.adapter as NewsListAdapter
            adapter.update(it)
        })
    }
}