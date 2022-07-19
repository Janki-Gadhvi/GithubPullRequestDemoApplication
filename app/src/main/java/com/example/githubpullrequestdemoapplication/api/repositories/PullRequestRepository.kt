package com.example.githubpullrequestdemoapplication.api.repositories

import com.example.githubpullrequestdemoapplication.api.ApiService
import javax.inject.Inject

class PullRequestRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun listPullRequests(page_number: Int) = apiService.getPullRequests(page_number)
}