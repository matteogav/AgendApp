package com.matteogav.agendapp.utils

import com.matteogav.agendapp.data.models.Info
import com.matteogav.agendapp.data.models.UserCoordinates
import com.matteogav.agendapp.data.models.UserId
import com.matteogav.agendapp.data.models.UserLocation
import com.matteogav.agendapp.data.models.UserModel
import com.matteogav.agendapp.data.models.UserName
import com.matteogav.agendapp.data.models.UserProfilePicture
import com.matteogav.agendapp.data.models.UserRegistered
import com.matteogav.agendapp.data.models.UserResponse
import com.matteogav.agendapp.data.models.UserStreet
import com.matteogav.agendapp.data.models.UserTimezone

class FakeUserResponse {
    companion object {
        fun createUser(): UserResponse {
            val user = UserModel(
                gender = "female",
                name = UserName("Miss", "Jennie", "Nichols"),
                location = UserLocation(
                    street = UserStreet(8929, "Valwood Pkwy"),
                    city = "Billings",
                    state = "Michigan",
                    country = "United States",
                    postcode = 63104,
                    coordinates = UserCoordinates("-69.8246", "134.8719"),
                    timezone = UserTimezone("+9:30", "Adelaide, Darwin")
                ),
                email = "test@example.com",
                registered = UserRegistered("2007-07-09T05:51:59.390Z", 14),
                cell = "654321890",
                id = UserId(name = null, value = null),
                picture = UserProfilePicture(
                    "https://randomuser.me/api/portraits/men/75.jpg",
                    "https://randomuser.me/api/portraits/med/men/75.jpg",
                    "https://randomuser.me/api/portraits/thumb/men/75.jpg")
            )

            val info = Info("56d27f4a53bd5441", 1, 1, "1.4")

            return UserResponse(listOf(user), info)
        }
    }
}