package org.example.project.cmp.feature.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.cmp.common.BaseViewModel
import org.example.project.cmp.feature.main.data.Rocket
import org.example.project.cmp.feature.main.data.RocketRepository
import org.example.project.cmp.feature.main.data.RocketRepositoryImpl

class MainViewModel : ViewModel() {
    private val repo : RocketRepository = RocketRepositoryImpl()

    private val _rockets = MutableStateFlow<List<Rocket>>(emptyList())
    val rockets : StateFlow<List<Rocket>> = _rockets

    init {
        loadRockets()
    }

    fun loadRockets() {
        _rockets.value = repo.getItems()
    }
}
