package com.example.socialconnect

import android.app.Application
import com.cloudinary.android.MediaManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = hashMapOf(
            "cloud_name" to getString(R.string.cloud_name), // Fetch actual string value
            "api_key" to getString(R.string.api_key),
            "api_secret" to getString(R.string.api_secret)
        )

        MediaManager.init(this, config) // Initialize Cloudinary with config
    }
}
