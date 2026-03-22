package org.example.project.cmp.feature.main.data

import org.example.project.cmp.feature.main.data.Objects.Rocket.RemoteCard

class CardRepository {


    suspend fun loadItems(): List<RemoteCard> {
//        return listOf(
//            RemoteCard(1, "Восток", 1961, Res.drawable.astronaut),
//            RemoteCard(2, "Восход", 1964, Res.drawable.astronaut),
//            RemoteCard(3, "Союз", 1967, Res.drawable.astronaut),
//            RemoteCard(4, "Apollo", 1968, Res.drawable.astronaut),
//            RemoteCard(5, "Space Shuttle", 1981, Res.drawable.astronaut)
//        )
        return listOf()
    }
}