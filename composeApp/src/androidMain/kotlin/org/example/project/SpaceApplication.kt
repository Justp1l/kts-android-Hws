package org.example.project

import android.app.Application
import org.example.project.cmp.common.DI.initKoin
import org.koin.android.ext.koin.androidContext

class SpaceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SpaceApplication)
        }
    }
}