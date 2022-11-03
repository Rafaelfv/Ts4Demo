package com.example.ts4_demo.data.models.login

data class LoginResponse(
    val kind: String,
    val localId: String,
    val email: String,
    val displayName: String,
    val idToken: String,
    val registered: String,
    val refreshToken: String,
    val expiresIn: String,
    val username: String,
    val salesforceUserId: String,
    val profile: String,
    val salesforceIdToken: String
)
