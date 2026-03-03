package org.example.project.cmp.feature.main.data

import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut

class RocketRepositoryImpl : RocketRepository{
    override fun getItems(): List<Rocket> {
        return listOf(
            Rocket(1, "Восток", 1961, Res.drawable.astronaut),
            Rocket(2, "Восход", 1964, Res.drawable.astronaut),
            Rocket(3, "Союз", 1967, Res.drawable.astronaut),
            Rocket(4, "Apollo", 1968, Res.drawable.astronaut),
            Rocket(5, "Space Shuttle", 1981, Res.drawable.astronaut)
        )
    }
}