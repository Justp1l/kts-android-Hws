package org.example.project.cmp.feature.main.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.cmp.app.Destination
import org.example.project.cmp.common.storage.AppStorage
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.main.data.AgenciesRepository
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency

@OptIn(FlowPreview::class)
class MainAgencyViewModel : ViewModel() {
    private val repo = AgenciesRepository()
    private val searchQueryFlow = MutableStateFlow("")
    private val _state = MutableStateFlow(MainAgencyUIState())
    private val _initialState = MutableStateFlow(MainAgencyUIState())
    val state: StateFlow<MainAgencyUIState> = _state.asStateFlow()

    init {
        loadAgency()
        viewModelScope.launch {
            searchQueryFlow
                .debounce(300L)
                .distinctUntilChanged()
                .onEach { query ->
                    _state.update {
                        it.copy(agencies = filterItems(query))
                    }
                }.collect()
        }
    }

    private var currentLoadJob: Job? = null
    fun loadAgency() {
        _state.update {
            it.copy(isLoading = true, error = null)
        }
        currentLoadJob = viewModelScope.launch {
            runCatching {
                repo.loadItems()
            }.onSuccess { agencies ->
                _state.update {
                    it.copy(isLoading = false, agencies = agencies)
                }
                _initialState.update {
                    it.copy(agencies = agencies)
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        agencies = emptyList(),
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    fun onQueryChange(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
        searchQueryFlow.value = query
    }

    fun onQueryClear() {
        onQueryChange("")
    }

    fun makeSearch() {
        if (_state.value.isSearchActive) {
            _state.update {
                it.copy(isSearchActive = false)
            }
        } else {
            _state.update {
                it.copy(isSearchActive = true)
            }
        }

    }

    private fun filterItems(query: String): List<AgencyEntity> {
        if (query.isBlank()) return _initialState.value.agencies
        return _initialState.value.agencies.filter {
            it.name.lowercase().contains(query.lowercase()) ||
                    it.ceo.lowercase().contains(query.lowercase())
        }
    }
}