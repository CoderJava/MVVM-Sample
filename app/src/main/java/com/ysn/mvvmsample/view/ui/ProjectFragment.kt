package com.ysn.mvvmsample.view.ui


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
import com.ysn.mvvmsample.databinding.FragmentProjectBinding
import com.ysn.mvvmsample.di.Injectable
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.view.viewmodel.ProjectViewModel
import javax.inject.Inject

class ProjectFragment : Fragment(), Injectable {

    private val TAG = javaClass.simpleName
    private lateinit var binding: FragmentProjectBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectViewModel::class.java)
        viewModel.projectID.value = arguments?.getString("project_id", "")

        binding.projectViewModel = viewModel
        binding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        viewModel.projectObservable.observe(this, Observer<Project> {
            if (it != null) {
                binding.isLoading = false
                viewModel.project.set(it)
            }
        })

    }


}
