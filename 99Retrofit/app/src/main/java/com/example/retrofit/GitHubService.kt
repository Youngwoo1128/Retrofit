package com.example.retrofit

import android.telecom.Call
import retrofit2.http.GET

interface GitHubService {
    @GET("users/Kotlin/repos")
    fun users(): retrofit2.Call<List<RepositoryItem>>
}