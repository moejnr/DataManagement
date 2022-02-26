package com.yeyannaung.datamanagement.model

import java.io.Serializable

data class User(
    var firstName: String,
    val lastName: String,
    val email: String,
    val dob: String,
    val gender: String,
    val nationality: String,
    val country: String,
    val phone: String
) : Serializable
