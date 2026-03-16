package org.example.project.cmp.feature.main.presentation.components

import androidx.compose.foundation.Image
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.main.data.Objects.Agency.Country
import org.example.project.cmp.feature.main.data.Objects.Agency.Logo
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency

class AgenciesPreview() {
    val agencies = mutableListOf<AgencyEntity>()

    init {
        for (i in 0..50) {
            agencies.add(
                AgencyEntity(
                    name = "$i",
                    abbrev = "U",
                    ceo = "You",
                    featured = true,
                    countryName = listOf(("USA")),
                    imageName = null,
                    imageURL = null,
                    description = "smth",
                    foundingYear = "${i + 1960}",
                    logo = "https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/russian2520federal2520space2520agency25202528roscosmos2529_logo_20190207032459.png"
                )
            )
        }
    }
}