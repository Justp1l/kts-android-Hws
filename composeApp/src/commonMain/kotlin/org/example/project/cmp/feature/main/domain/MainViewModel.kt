package org.example.project.cmp.feature.main.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainUIState (
    val searchQuery: String = ""
)

class MainViewModel : ViewModel() {
    private val searchQueryFlow = MutableStateFlow("")
    private val _state = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _state.update {
            it.copy(searchQuery = newQuery)
        }
        searchQueryFlow.value = newQuery
    }
    fun queryClear(){
        onQueryChange("")
    }
}
