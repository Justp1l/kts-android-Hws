package org.example.project.cmp.feature.main.data

import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.common.storage.database.DatabaseProvider
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency
import org.example.project.cmp.feature.main.data.net.ApiInteraction
import org.example.project.cmp.feature.main.data.net.Networking

class AgenciesRepository {

    private val agencyDao = DatabaseProvider.instance.agencyDao()
    val Api = ApiInteraction(
        url = "2.3.0/agencies",
        httpClient = Networking.httpClient
    )

    suspend fun loadItems(): List<AgencyEntity> {
        val localAgencies = agencyDao.getAllAgencies()
        if (localAgencies.isEmpty()) {
            val initialInteraction = Api.interactionWithNet(tag = "?limit=70")
            saveAgencies(
                initialAgencies = initialInteraction.items,
                size = initialInteraction.size
            )
            return agencyDao.getAllAgencies()
        }
        return localAgencies
    }

    @Suppress("SuspiciousIndentation")
    suspend fun saveAgencies(initialAgencies: List<RemoteAgency>, size: Int) {
        agencyDao.insertAgency(initialAgencies.map { it.toEntity() })
        var currentOffset = initialAgencies.size
        while (currentOffset < size) {
            val response = Api.interactionWithNet(tag = "?limit=70&offset=$currentOffset").items
            if (response.isEmpty()){
                break
            }
            agencyDao.insertAgency(response.map { it.toEntity() })
            currentOffset += response.size
        }

    }

    suspend fun search(query: String): List<RemoteAgency> {
        return Api.searchAgencies(query).items
    }
}

fun RemoteAgency.toEntity(): AgencyEntity {
    val countryList: MutableList<String> = mutableListOf()
    country?.forEach { country ->
        countryList.add(country.countryName)
    }
    return AgencyEntity(
        name = name,
        abbrev = abbrev,
        ceo = ceo,
        featured = featured,
        countryName = countryList,
        imageName = image?.imageName,
        imageURL = image?.imageURL,
        description = description,
        foundingYear = foundingYear,
        logo = logo?.link,
    )
}