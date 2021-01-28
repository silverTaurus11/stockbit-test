package com.project.bibit_test.data.source.local.sharedpref.model

data class ProfileItem(
    val userName: String?= "",
    val password: String?= "",
    val loginWith: String?= "",
    val tokenThirdParty: String?= ""
)