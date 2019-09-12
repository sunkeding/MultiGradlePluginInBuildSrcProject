package com.keding.plugins.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author: skd* @date 2019-07-03
 * @Desc MySecondPlugin
 */

class MySecondPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("自定义插件MySecondPlugin执行了！！！" + " project name:" + project.getName())
    }
}

