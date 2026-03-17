package org.example.project.cmp.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.main.data.AgenciesRepository
import kotlin.coroutines.cancellation.CancellationException

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
            suspendRunCatching {
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
    inline fun <R> suspendRunCatching(block: () -> R): Result<R> {
        return try {
            Result.success(block())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}