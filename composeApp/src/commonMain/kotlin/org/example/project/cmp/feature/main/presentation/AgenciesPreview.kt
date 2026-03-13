package org.example.project.cmp.feature.main.presentation

import org.example.project.cmp.feature.main.data.Objects.Agency.Logo
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency

class AgenciesPreview() {
    val agencies = mutableListOf<RemoteAgency>()
    init {
        for (i in 0..50) {
            agencies.add(
                RemoteAgency(
                    "$i", "Elon", "${i + 1}", "${i + 2}",
                    Logo("https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/spacex_nation_20230531064544.jpg")
                )
            )
        }
    }
}