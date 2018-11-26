package com.ysn.mvvmsample.view.activity.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.databinding.ItemUserBinding
import com.ysn.mvvmsample.domain.model.searchuser.Item

class AdapterUser constructor(private var users: List<Item>) : RecyclerView.Adapter<AdapterUser.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = users[position]
    }

    override fun getItemCount(): Int = users.size

    fun refresh(users: List<Item>?) {
        this.users = users!!
        notifyDataSetChanged()
    }

    class ViewHolder constructor(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

}