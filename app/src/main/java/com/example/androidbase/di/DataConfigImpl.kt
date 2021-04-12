package com.example.androidbase.di

import com.example.data.config.DataConfig
import javax.inject.Inject

class DataConfigImpl @Inject constructor(): DataConfig {
    override fun getApiKey(): String {
        return "" // provides the api key needed for authentication
    }

    override fun getBaseUrl(): String {
        return "https://api.doordash.com/" // provides the base url
    }
}