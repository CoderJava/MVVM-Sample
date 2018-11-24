package com.ysn.mvvmsample.di

import com.ysn.mvvmsample.service.repository.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideGithubServide(): GithubService =
        Retrofit.Builder()
            .baseUrl(GithubService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

}