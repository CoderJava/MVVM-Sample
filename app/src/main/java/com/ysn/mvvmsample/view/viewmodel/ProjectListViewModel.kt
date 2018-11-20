package com.ysn.mvvmsample.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.service.repository.ProjectRepository

class ProjectListViewModel constructor(application: Application): AndroidViewModel(application) {

    private val projectListObservable: LiveData<List<Project>> = ProjectRepository.projectRepository
            .getProjectList("Google")

    fun getProjectListObservable(): LiveData<List<Project>> {
        return projectListObservable
    }

}