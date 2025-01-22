package com.example.socialconnect

import android.app.Application
import com.cloudinary.android.MediaManager

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val config = hashMapOf(
            "cloud_name" to R.string.cloud_name,
            "api_key" to R.string.api_key,
            "api_secret" to R.string.api_secret
        )
        MediaManager.init(this, config)
    }
}