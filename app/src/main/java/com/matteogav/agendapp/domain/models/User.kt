package com.matteogav.agendapp.domain.models

import com.matteogav.agendapp.data.models.UserModel
import com.matteogav.agendapp.utils.parseDate
import com.matteogav.agendapp.utils.parsePhoneString
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val gender: Gender? = null,
    val image: String? = null,
    val registrationDate: String? = null,
    val cell: String? = null,
    val latitude: String? = null,
    val longitude: String? = null
)

enum class Gender {
    Female,
    Male
}

fun UserModel.toDomain() =
    User(
        id.value,
        "${name.first} ${name.last}",
        email,
        if(gender=="male") Gender.Male
        else Gender.Female,
        picture.large,
        registered.date.parseDate(),
        cell.parsePhoneString(),
        location.coordinates.latitude,
        location.coordinates.longitude,
    )
