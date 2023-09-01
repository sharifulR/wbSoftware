package com.wb.wbsoftware.viewModels.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wb.wbsoftware.models.leads.AllLeadResponse
import com.wb.wbsoftware.network.AllLeadAPI
import com.wb.wbsoftware.network.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class AllLeadRepository @Inject constructor(private val allLeadApi: AllLeadAPI) {

    private val _leadLiveData= MutableLiveData<NetworkResult<AllLeadResponse>>()
    val leadLiveData: LiveData<NetworkResult<AllLeadResponse>>
        get() = _leadLiveData

    suspend fun getAllLeads(){
        val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMDU1ZjFhYjk2YWFkOGFjOTdkZTE1NjhjMjZiOTM2NzNmNjgzNTI3MmNkYTdlMDIzZjAxYTM3YTA2OTRmZDY4ZWYzOGRmOTYyNzViMzk4YzQiLCJpYXQiOjE2OTMwNDYyNjAuMDQ4MDIxMDc4MTA5NzQxMjEwOTM3NSwibmJmIjoxNjkzMDQ2MjYwLjA0ODAyMjAzMTc4NDA1NzYxNzE4NzUsImV4cCI6MTcyNDY2ODY2MC4wNDUyMDcwMjM2MjA2MDU0Njg3NSwic3ViIjoiMiIsInNjb3BlcyI6W119.ZXvIWaebY0zSaV7dRWaNZkf79ra9XetrCYUjwBxfJjtVDLMiuqRWq_q_X6mBotJSBOLu8eB8x36b4NlWzlmsGGNG5GudTkNNBn22hkdJ_u-ymGNhotjFFl1nKEJUZOJYwEr33N_4R5iOBczhbaz1LSRF54ENwxcQA-vdWKFn5nPC9OALZ8YGBRaYBj2j_RCothVwTnyno3rzjcxl5K7s4ORWZIfvYmfiq2Dz13pRUrQLone700Q8FK5zff4w0M8dikuATEqQq7FwBxfY9k1nHDLNHu9vQlh7daK2I0KQ2szDxmWuQdqGuFHnSNh7a9HQf5AH-qqK9gBlLBxbg8URl9_SzVXJ7rhVa0NAl-KeCEIkp8vLyowv9dbA4dD-PVGviqsEKS__-en4qNBouAczlXCXhjd7zyZXQb3VmvQqDul4rJRoYysF5TMkbfnO69P9oGcqNjVYSi8gZcfVfbTy6tTokA6gF0qM_W3boHJsv4fsN6TtvkC3sJXluOIydRa94FgJQEsehzZMINebe8csjuTGeN5JCFWJ1bPI8LlA5BL3ryJNE09pDVEhviH74wc8VVTtZg5YHJKvP6MJLoPZbediQj8ld9xzAGTZ5c7SeZEMb2xo1RZhBYliZOteYv3mh6rNbvTeCLP2mvYoJ8hrzIusG9-xVWhXhEdv5npe8o4"
        //var r=0
        _leadLiveData.postValue(NetworkResult.Loading())
        var response=allLeadApi.getAllLead(token,1)
        //r = response.body()!!.data.currentPage

        if (response.isSuccessful && response.body() != null) {
            _leadLiveData.postValue(NetworkResult.Success(
                response.body()!!)
            )

            Log.d(Constraints.TAG, "login: ${response.body()}")
            Log.d("TAG", "getAllClass: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _leadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "login: ${errorObj}")
        } else {
            _leadLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }
}