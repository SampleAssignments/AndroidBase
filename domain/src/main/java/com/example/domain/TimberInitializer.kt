package com.example.domain

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

private const val LOG_PREFIX = "sample-app"

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.uprootAll()
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, LOG_PREFIX + tag, message, t)
            }

            override fun createStackElementTag(element: StackTraceElement): String {
                return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
            }
        })
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}