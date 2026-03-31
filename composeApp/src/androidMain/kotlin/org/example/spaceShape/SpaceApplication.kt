package org.example.spaceShape

import android.app.Application
import com.google.firebase.FirebaseApp
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.spaceShape.cmp.app.CrashlyticsAntilog
import org.example.spaceShape.cmp.common.DI.initKoin
import org.koin.android.ext.koin.androidContext

class SpaceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseApp.getInstance().isDataCollectionDefaultEnabled = true
        Napier.base(DebugAntilog())
        Napier.base(CrashlyticsAntilog())
        initKoin {
            androidContext(this@SpaceApplication)
        }
    }
}