package com.example.githubpullrequestdemoapplication.ui.view.activities

import PullRequestAdapterAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubpullrequestdemoapplication.R
import com.example.githubpullrequestdemoapplication.api.models.PullRequest
import com.example.githubpullrequestdemoapplication.api.models.Resource
import com.example.githubpullrequestdemoapplication.databinding.ActivityMainBinding
import com.example.githubpullrequestdemoapplication.ui.viewmodels.PullRequestViewModel
import com.example.githubpullrequestdemoapplication.utils.CommonViews
import com.example.githubpullrequestdemoapplication.utils.EndlessRecyclerOnScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pullRequestViewModel by viewModels<PullRequestViewModel>()
    private lateinit var pullRequestAdapterAdapter: PullRequestAdapterAdapter
    private var pullRequestsList: ArrayList<PullRequest> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews() {

        val mLayoutManager = LinearLayoutManager(this)
        pullRequestAdapterAdapter = PullRequestAdapterAdapter(pullRequestsList, this)
        binding.rvPullRequest.layoutManager = mLayoutManager
        binding.rvPullRequest.adapter = pullRequestAdapterAdapter
        pullRequestsList.clear()

        binding.rvPullRequest.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                listPullRequests()
            }
        })

        startObservers()
        listPullRequests()
    }

    fun startObservers() {
        pullRequestViewModel.listPullRequestResponse.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    CommonViews.startLoading(this)
                }
                is Resource.Success -> {
                    CommonViews.hideLoading()
                    setData(it.value)
                }
                is Resource.Failure -> {
                    CommonViews.hideLoading()
                    Toast.makeText(this, it.errorBody.toString(), Toast.LENGTH_LONG)
                }
            }
        }
    }

    fun listPullRequests() {
        pullRequestViewModel.listPullRequest()
    }

    fun setData(list: ArrayList<PullRequest>) {
        pullRequestsList.addAll(list)
        pullRequestAdapterAdapter?.notifyDataSetChanged()
    }


}