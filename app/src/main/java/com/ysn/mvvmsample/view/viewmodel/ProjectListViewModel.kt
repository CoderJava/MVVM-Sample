package com.ysn.mvvmsample.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.service.repository.ProjectRepository
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(projectRepository: ProjectRepository,
                                               application: Application): AndroidViewModel(application) {

    val projectListObservable: LiveData<List<Project>> = projectRepository
            .getProjectList("Google")

}