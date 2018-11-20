package com.ysn.mvvmsample.service.repository

import com.ysn.mvvmsample.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    companion object {
        val baseUrl = "https://api.github.com/"
    }

    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") reponame: String): Call<Project>

}