package com.where.to.go.main.vms

import androidx.lifecycle.ViewModel
import com.where.to.go.internet.models.Party

class ScheduleViewModel: ViewModel() {
    val partyList = listOf(
        Party(
            id = 1,
            ownerId = 1001,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTRgUPG8RubS8Z3lAG1lGlwJyudTbg6KXoeEyfQHe0QktRH3ahrGyMI0FnK0tNGPuUd0w&usqp=CAU",
            about = "Незабываемая вечеринка на крыше с видом на город!",
            tg = "@RoofParty",
            price = 1500,
            theme = "Neon Nights",
            name = "Ночная тусовка",
            date = "2025-03-01T20:00:00",
            maxGuests = 50,
            status = 1,
            created = "2025-02-21T10:00:00"
        ),
        Party(
            id = 2,
            ownerId = 1002,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmwGQR00tPmGzacLSknci2U1sHXtIWqDbpSNYrPVjsSQjdcCaOaY-u-jwjW3eVVpF3ES8&usqp=CAU",
            about = "Темная вечеринка в стиле gothic.",
            tg = null,
            price = 2000,
            theme = "Gothic Vibes",
            name = "Тьма и тайны",
            date = "2025-03-15T22:00:00",
            maxGuests = 30,
            status = 0,
            created = "2025-02-20T15:30:00"
        ),
        Party(
            id = 3,
            ownerId = 1003,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaZFp_HtcOilLOQIgCyl2yRqCiG4TY-WyYP1RSfeReT_4T3eDOWzh77-kXpd39DBfhnmQ&usqp=CAU",
            about = "Дневная вечеринка у бассейна с коктейлями.",
            tg = "@PoolParty2025",
            price = 1000,
            theme = "Summer Splash",
            name = "Бассейн и солнце",
            date = "2025-06-10T14:00:00",
            maxGuests = 100,
            status = 1,
            created = "2025-02-21T09:15:00"
        )
    )
}