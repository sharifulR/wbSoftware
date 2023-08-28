package com.wb.wbsoftware.networks

import com.wb.wbsoftware.models.auth.LoginRequest
import com.wb.wbsoftware.models.auth.LoginResponse
import com.wb.wbsoftware.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


}