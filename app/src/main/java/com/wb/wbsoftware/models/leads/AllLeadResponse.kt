package com.wb.wbsoftware.models.leads


import com.google.gson.annotations.SerializedName

data class AllLeadResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("userData")
    val userData: List<UserData>
) {
    data class Data(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("first_page_url")
        val firstPageUrl: String,
        @SerializedName("from")
        val from: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("last_page_url")
        val lastPageUrl: String,
        @SerializedName("links")
        val links: List<Link>,
        @SerializedName("next_page_url")
        val nextPageUrl: Any,
        @SerializedName("path")
        val path: String,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("prev_page_url")
        val prevPageUrl: Any,
        @SerializedName("to")
        val to: Int,
        @SerializedName("total")
        val total: Int
    ) {
        data class Data(
            @SerializedName("address_1")
            val address1: String,
            @SerializedName("address_2")
            val address2: Any,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("district_data")
            val districtData: DistrictData,
            @SerializedName("district_id")
            val districtId: Int,
            @SerializedName("email")
            val email: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("installation_charge")
            val installationCharge: String,
            @SerializedName("interest")
            val interest: String,
            @SerializedName("mobile_1")
            val mobile1: String,
            @SerializedName("mobile_2")
            val mobile2: Any,
            @SerializedName("monthly_charge")
            val monthlyCharge: String,
            @SerializedName("organization")
            val organization: String,
            @SerializedName("project_data")
            val projectData: ProjectData,
            @SerializedName("project_id")
            val projectId: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("user_id")
            val userId: Int
        ) {
            data class DistrictData(
                @SerializedName("code")
                val code: String,
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("stateId")
                val stateId: Int,
                @SerializedName("updated_at")
                val updatedAt: String
            )

            data class ProjectData(
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("image")
                val image: String,
                @SerializedName("project_description")
                val projectDescription: String,
                @SerializedName("project_name")
                val projectName: String,
                @SerializedName("updated_at")
                val updatedAt: String,
                @SerializedName("user_id")
                val userId: Int
            )
        }

        data class Link(
            @SerializedName("active")
            val active: Boolean,
            @SerializedName("label")
            val label: String,
            @SerializedName("url")
            val url: String
        )
    }

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