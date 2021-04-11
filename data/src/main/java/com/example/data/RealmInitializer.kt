package com.example.data

import android.content.Context
import androidx.startup.Initializer
import io.realm.Realm

class RealmInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Realm.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}