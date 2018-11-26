package com.ysn.mvvmsample.view.activity.main

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.ysn.mvvmsample.api.GithubApi
import com.ysn.mvvmsample.domain.model.searchuser.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    private val githubApi: GithubApi
) : AndroidViewModel(application) {

    var users = MutableLiveData<List<Item>>()

    @SuppressLint("CheckResult")
    fun searchUser(query: String) {
        githubApi.searchUser(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    users.value = it.items
                },
                {
                    users.value = null
                },
                {
                    /* nothing to do in here */
                }
            )
    }

    fun ubahData() {
        val temp = ArrayList<Item>()
        temp.add(Item(login = "Testing"))
        users.value = temp
    }

}