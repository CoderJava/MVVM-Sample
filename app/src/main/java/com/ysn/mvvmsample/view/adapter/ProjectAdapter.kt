package com.ysn.mvvmsample.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.databinding.ProjectListItemBinding
import com.ysn.mvvmsample.service.model.Project
import com.ysn.mvvmsample.view.callback.ProjectClickCallback

class ProjectAdapter constructor(private val projectClickCallback: ProjectClickCallback) :
        RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    var projectList: List<Project>? = null
        set(value) {
            if (field == null) {
                field = value
                notifyItemRangeInserted(0, value!!.size)
            } else {
                val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        field!![oldItemPosition].id == value!![newItemPosition].id

                    override fun getOldListSize(): Int = field!!.size

                    override fun getNewListSize(): Int = value!!.size

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val project = value!![newItemPosition]
                        val old = field!![oldItemPosition]
                        return project.id == old.id && project.git_url == old.git_url
                    }
                })
                field = value
                result.dispatchUpdatesTo(this)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<ProjectListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.project_list_item,
                parent,
                false
        )
        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = projectList!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return projectList?.size ?: 0
    }

    inner class ProjectViewHolder constructor(var binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)

}