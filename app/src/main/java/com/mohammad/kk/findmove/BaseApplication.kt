package com.mohammad.kk.findmove

import android.app.Application
import com.mohammad.kk.findmove.util.rootSnackBarProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(rootSnackBarProvider)
        }

    }
}