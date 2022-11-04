package com.example.ts4_demo

data class ResponseSignup (
    val uid: String,
    val username: String,
    val email: String,
    val firstName: String,
    val firstSurname: String,
    val secondSurname: String,
    val profile: String,
    val salesforceUserId: String,
    val emailVerified: String,
    val displayName: String
)