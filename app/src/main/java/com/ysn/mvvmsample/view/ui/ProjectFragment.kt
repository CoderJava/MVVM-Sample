package com.ysn.mvvmsample.view.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.databinding.FragmentProjectBinding
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.view.viewmodel.ProjectViewModel

class ProjectFragment : Fragment() {

    private val TAG = javaClass.simpleName
    private lateinit var binding: FragmentProjectBinding
    companion object {
        fun forProject(projectId: String): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()

            args.putString("project_id", projectId)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_project,
                container,
                false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ProjectViewModel.Factory(
                activity!!.application,
                arguments!!.getString("project_id", "")
        )

        val viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectViewModel::class.java)

        binding.projectViewModel = viewModel
        binding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        viewModel.getObservableProject().observe(this, Observer<Project> {
            if (it != null) {
                binding.isLoading = false
                viewModel.setProject(it)
            }
        })

    }


}
