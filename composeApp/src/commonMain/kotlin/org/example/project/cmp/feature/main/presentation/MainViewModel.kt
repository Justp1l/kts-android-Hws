package org.example.project.cmp.feature.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.cmp.feature.main.data.Objects.Rocket.RemoteCard
import org.example.project.cmp.feature.main.data.CardRepository

class MainViewModel : ViewModel() {
    private val repo = CardRepository()

    private val _rockets = MutableStateFlow<List<RemoteCard>>(emptyList())
    val rockets: StateFlow<List<RemoteCard>> = _rockets


//    init {
//        loadRockets()
//    }

    suspend fun loadRockets() {
        _rockets.value = repo.loadItems()
    }
}
