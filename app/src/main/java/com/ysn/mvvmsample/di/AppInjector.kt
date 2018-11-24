package com.ysn.mvvmsample.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.ysn.mvvmsample.App
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class AppInjector {

    companion object {
        fun init(app: App) {
            app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity?) {
                    /* nothing to do in here */
                }

                override fun onActivityResumed(activity: Activity?) {
                    /* nothing to do in here */
                }

                override fun onActivityPaused(activity: Activity?) {
                    /* nothing to do in here */
                }

                override fun onActivityStopped(activity: Activity?) {
                    /* nothing to do in here */
                }

                override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
                    /* nothing to do in here */
                }

                override fun onActivityDestroyed(activity: Activity?) {
                    /* nothing to do in here */
                }

            })
        }

        private fun handleActivity(activity: Activity?) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                        .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                                if (fragment is Injectable) {
                                    AndroidSupportInjection.inject(fragment)
                                }
                            }
                        }, true)
            }
        }
    }

}