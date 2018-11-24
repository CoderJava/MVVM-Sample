package com.ysn.mvvmsample.view.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.service.repository.ProjectRepository
import javax.inject.Inject

class ProjectViewModel @Inject constructor(projectRepository: ProjectRepository,
                                           application: Application) : AndroidViewModel(application) {


    var projectObservable: LiveData<Project>
    var projectID: MutableLiveData<String> = MutableLiveData()
    val project = ObservableField<Project>()

    init {
        projectObservable = Transformations.switchMap(projectID) { input ->
            if (input.isEmpty()) {
                val absent = MutableLiveData<Project>()
                absent.value = null
                return@switchMap absent
            }
            return@switchMap projectRepository.getProjectDetails("Google", projectID.value!!)
        }
    }

}