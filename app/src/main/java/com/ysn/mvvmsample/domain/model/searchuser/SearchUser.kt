package com.ysn.mvvmsample.domain.model.searchuser

import com.google.gson.annotations.SerializedName

data class SearchUser(
        @SerializedName("incomplete_results")
        val incompleteResults: Boolean = false,
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("total_count")
        val totalCount: Int = 0
)

data class Item(
        @SerializedName("avatar_url")
        val avatarUrl: String = "",
        @SerializedName("events_url")
        val eventsUrl: String = "",
        @SerializedName("followers_url")
        val followersUrl: String = "",
        @SerializedName("following_url")
        val followingUrl: String = "",
        @SerializedName("gists_url")
        val gistsUrl: String = "",
        @SerializedName("gravatar_id")
        val gravatarId: String = "",
        @SerializedName("html_url")
        val htmlUrl: String = "",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("login")
        val login: String = "",
        @SerializedName("node_id")
        val nodeId: String = "",
        @SerializedName("organizations_url")
        val organizationsUrl: String = "",
        @SerializedName("received_events_url")
        val receivedEventsUrl: String = "",
        @SerializedName("repos_url")
        val reposUrl: String = "",
        @SerializedName("score")
        val score: Double = 0.0,
        @SerializedName("site_admin")
        val siteAdmin: Boolean = false,
        @SerializedName("starred_url")
        val starredUrl: String = "",
        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String = "",
        @SerializedName("type")
        val type: String = "",
        @SerializedName("url")
        val url: String = ""
)