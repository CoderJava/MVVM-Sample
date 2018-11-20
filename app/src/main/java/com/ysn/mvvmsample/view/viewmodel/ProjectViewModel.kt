package com.ysn.mvvmsample.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.service.repository.ProjectRepository

class ProjectViewModel constructor(application: Application,
                                   projectId: String): AndroidViewModel(application){

    private val projectObservable = ProjectRepository.projectRepository
            .getProjectDetails("Google", projectId)
    var project = ObservableField<Project>()

    fun setProject(project: Project) {
        this.project.set(project)
    }

    fun getObservableProject(): MutableLiveData<Project> {
        return projectObservable
    }

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(val application: Application, private val projectId: String): ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProjectViewModel(application, projectId) as T
        }

    }
}