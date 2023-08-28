package com.wb.wbsoftware.models.auth


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("userData")
    val userData: UserData
) {
    data class UserData(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("deleted_at")
        val deletedAt: Any,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: Any,
        @SerializedName("last_login")
        val lastLogin: Any,
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_type")
        val userType: String,
        @SerializedName("verify_code")
        val verifyCode: Any,
        @SerializedName("verify_expires_at")
        val verifyExpiresAt: Any
    )
}