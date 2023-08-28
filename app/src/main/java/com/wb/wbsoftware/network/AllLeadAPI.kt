package com.wb.wbsoftware.network

import com.wb.wbsoftware.models.leads.AllLeadResponse
import com.wb.wbsoftware.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AllLeadAPI {
    @Headers("Content-Type: application/json")
    @GET(Constants.AllLead_ENDPOINT)
    suspend fun getAllLead(): Response<AllLeadResponse>
}