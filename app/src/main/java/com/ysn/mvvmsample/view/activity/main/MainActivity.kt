package com.ysn.mvvmsample.view.activity.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.databinding.ActivityMainBinding
import com.ysn.mvvmsample.domain.model.searchuser.Item
import com.ysn.mvvmsample.view.activity.main.adapter.AdapterUser
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
        )

        val data = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        binding.isLoading = false
        binding.data = data

        val list = ArrayList<Item>()
        val adapterUser = AdapterUser(list)
        binding.recyclerViewResultQuery.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewResultQuery.adapter = adapterUser
        data.users.observe(this, Observer {
            binding.isLoading = false
            adapterUser.refresh(it)
        })

        binding.editTextQuery.setOnEditorActionListener { textView, actionId, keyEvent ->
            /*if (textView.text.isEmpty()) {
                Toast.makeText(this, "Query empty", Toast.LENGTH_SHORT)
                        .show()
            } else if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.isLoading = true
                data.searchUser(textView.text.toString())
            }*/
            data.ubahData()
            true
        }
    }
}
