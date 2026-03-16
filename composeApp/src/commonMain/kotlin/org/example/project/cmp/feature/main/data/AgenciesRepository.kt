package org.example.project.cmp.feature.main.data

import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.common.storage.database.DatabaseProvider
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency
import org.example.project.cmp.feature.main.data.net.ApiInteraction
import org.example.project.cmp.feature.main.data.net.Networking
import kotlin.String

class AgenciesRepository {

    private val agencyDao = DatabaseProvider.instance.agencyDao()
    val Api = ApiInteraction(
        url = "2.3.0/agencies",
        httpClient = Networking.httpClient
    )

    suspend fun loadItems(): List<AgencyEntity> {
        val localAgencies = agencyDao.getAllAgencies()
        if (localAgencies.isEmpty()) {
            val remoteAgencies = Api.initialInteraction(tag = "?featured=true&limit=50").items
            saveAgencies(remoteAgencies)
            return agencyDao.getAllAgencies()
        }
        return localAgencies
    }

    suspend fun saveAgencies(agencies: List<RemoteAgency>) {
        val list: MutableList<AgencyEntity> = mutableListOf()
        agencies.forEach {
            list.add(it.toEntity())
        }
        agencyDao.insertAgency(list)
    }

    suspend fun search(query: String): List<RemoteAgency> {
        return Api.searchAgencies(query).items
    }
}

fun RemoteAgency.toEntity(): AgencyEntity {
    val countryList: MutableList<String> = mutableListOf()
    country.forEach { country->
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
        logo = logo.link,
    )
}