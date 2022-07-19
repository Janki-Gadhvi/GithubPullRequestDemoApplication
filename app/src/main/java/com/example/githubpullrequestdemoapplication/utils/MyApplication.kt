package com.example.githubpullrequestdemoapplication.utils

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()


    }


}