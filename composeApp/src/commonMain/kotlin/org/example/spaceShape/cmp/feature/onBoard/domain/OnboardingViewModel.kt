package org.example.spaceShape.cmp.feature.onBoard.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import org.example.spaceShape.cmp.app.Destination
import org.example.spaceShape.cmp.feature.onBoard.data.AppStorage

class OnboardingViewModel(private val appStorage: AppStorage) : ViewModel() {
    suspend fun getStartDestination(): Destination {
        val isFirstLaunch = appStorage.isFirstLaunch.first()
        return if (isFirstLaunch) Destination.Onboarding else Destination.FeatureNavigation
    }
}