package com.ysn.mvvmsample.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ysn.mvvmsample.R
import com.ysn.mvvmsample.service.model.Project
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment::class.java.simpleName)
                    .commit()
        }
    }

    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager.beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
