package com.example.githubpullrequestdemoapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubpullrequestdemoapplication.api.ApiService
import com.example.githubpullrequestdemoapplication.api.models.PullRequest
import com.example.githubpullrequestdemoapplication.api.models.Resource
import com.example.githubpullrequestdemoapplication.api.repositories.PullRequestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PullRequestViewModel @Inject constructor(private val pullRequestRepository: PullRequestRepository) :
    ViewModel() {

    private val _listPullRequestResponse: MutableLiveData<Resource<ArrayList<PullRequest>>> =
        MutableLiveData()

    val listPullRequestResponse: LiveData<Resource<ArrayList<PullRequest>>>
        get() = _listPullRequestResponse

    private var isQueryExhausted = false // to keep track of paginated data
    private var pageNumber = 0
    fun listPullRequest() = viewModelScope.launch {
        //lets keep increasing the page numbers for pagination
        pageNumber++
        if (!isQueryExhausted) {
            withContext(Dispatchers.IO) {
                _listPullRequestResponse.postValue(Resource.Loading)
                val response = pullRequestRepository.listPullRequests(pageNumber)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.size < 30) // default github API per page size is 30
                            isQueryExhausted = true
                        _listPullRequestResponse.postValue(Resource.Success(it))
                    }
                } else {
                    _listPullRequestResponse.postValue(
                        Resource.Failure(response.code(), response.errorBody().toString())
                    )
                }
            }
        }
    }

}