package org.example.spaceShape.cmp.feature.main.agencies.data

import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgenciesDao
import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgencyEntity
import org.example.spaceShape.cmp.feature.main.agencies.data.objects.RemoteAgency
import org.example.spaceShape.cmp.common.net.ApiInteraction

class AgenciesRepositoryImpl(
    private val api: ApiInteraction,
    private val agencyDao : AgenciesDao
) : AgenciesRepository {

    override suspend fun loadItems(): List<AgencyEntity> {

        val localAgencies = agencyDao.getAllAgencies()
        if (localAgencies.isEmpty()) {
            val initialInteraction = api.interactionWithNet(tag = "agencies?limit=70")
            saveAgencies(
                initialAgencies = initialInteraction.items,
                size = initialInteraction.size
            )
            return agencyDao.getAllAgencies()
        }
        return localAgencies
    }

    @Suppress("SuspiciousIndentation")
    override suspend fun saveAgencies(initialAgencies: List<RemoteAgency>, size: Int) {
        agencyDao.insertAgency(initialAgencies.map { it.toEntity() })
        var currentOffset = initialAgencies.size
        while (currentOffset < size) {
            val response = api.interactionWithNet(tag = "agencies?limit=70&offset=$currentOffset").items
            if (response.isEmpty()){
                break
            }
            agencyDao.insertAgency(response.map { it.toEntity() })
            currentOffset += response.size
        }
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