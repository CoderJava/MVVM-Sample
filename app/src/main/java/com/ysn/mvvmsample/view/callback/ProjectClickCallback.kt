package com.ysn.mvvmsample.view.callback

import com.ysn.mvvmsample.service.model.Project

interface ProjectClickCallback {

    fun onClick(project: Project)

}