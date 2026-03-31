package org.example.spaceShape.cmp.feature.main.agencies.domain

import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgencyEntity

data class MainAgencyUIState(
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val agencies: List<AgencyEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAllButtonEnabled: Boolean = true,
)