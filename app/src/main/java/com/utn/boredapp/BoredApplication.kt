package com.utn.boredapp

import android.app.Application
import com.utn.boredapp.data.AppContainer

class BoredApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}