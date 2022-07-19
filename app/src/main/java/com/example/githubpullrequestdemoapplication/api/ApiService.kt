package com.example.githubpullrequestdemoapplication.api

import com.example.githubpullrequestdemoapplication.api.models.PullRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("repos/android/architecture-samples/pulls?state=closed")
    suspend fun getPullRequests(
        @Query("page") pageNumber: Int
    ): Response<ArrayList<PullRequest>>
}