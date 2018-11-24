package com.ysn.mvvmsample.view.ui


import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.databinding.FragmentProjectListBinding
import com.ysn.mvvmsample.di.Injectable
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.view.adapter.ProjectAdapter
import com.ysn.mvvmsample.view.callback.ProjectClickCallback
import com.ysn.mvvmsample.view.viewmodel.ProjectListViewModel
import javax.inject.Inject

class ProjectListFragment : Fragment(), Injectable {

    private val TAG = javaClass.simpleName
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var binding: FragmentProjectListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_project_list,
                container,
                false
        )

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectListObservable.observe(this, Observer<List<Project>> {
            if (it != null) {
                binding.isLoading = false
                projectAdapter.projectList = it
            }
        })
    }

}
