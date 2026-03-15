package org.example.project.cmp.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import org.example.project.cmp.app.Destination
import org.example.project.cmp.common.storage.AppStorage

class MainViewModel(private val appStorage: AppStorage = AppStorage()) : ViewModel() {

    suspend fun getStartDestination(): Destination {
        val isFirstLaunch = appStorage.isFirstLaunch.first()
        return if (isFirstLaunch) Destination.Onboarding else Destination.MainAgency
    }
}