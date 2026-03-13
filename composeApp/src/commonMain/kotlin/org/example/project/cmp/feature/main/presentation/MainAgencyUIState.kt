package org.example.project.cmp.feature.main.presentation

import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency

data class MainAgencyUIState(
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val agencies: List<RemoteAgency> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)