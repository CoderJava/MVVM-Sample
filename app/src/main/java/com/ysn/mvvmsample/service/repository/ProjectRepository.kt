package com.ysn.mvvmsample.service.repository

import android.arch.lifecycle.MutableLiveData
import com.ysn.mvvmsample.service.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository {

    var githubService: GithubService
    companion object {
        val projectRepository: ProjectRepository = ProjectRepository()
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(GithubService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubService = retrofit.create(GithubService::class.java)
    }

    fun getProjectList(userId: String): MutableLiveData<List<Project>> {
        val data = MutableLiveData<List<Project>>()
        githubService.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {
                data.value = response?.body()
            }

            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }

    fun getProjectDetails(userId: String, projectName: String): MutableLiveData<Project> {
        val data = MutableLiveData<Project>()
        githubService.getProjectDetails(userId, projectName).enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {
                simulateDelay()
                data.value = response?.body()
            }

            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}