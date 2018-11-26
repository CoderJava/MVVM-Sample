package com.ysn.mvvmsample.api

import com.ysn.mvvmsample.domain.model.searchuser.SearchUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val baseUrl = "https://api.github.com/"
    }

    @GET("search/users")
    fun searchUser(@Query("q") q: String): Observable<SearchUser>

}