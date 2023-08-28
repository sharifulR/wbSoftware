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

        _leadLiveData.postValue(NetworkResult.Loading())
        val response=allLeadApi.getAllLead()

        if (response.isSuccessful && response.body() != null) {
            _leadLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(Constraints.TAG, "login: ${response.body()}")
            Log.d("TAG", "getAllClass: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            //_loginResponseLiveData.postValue(NetworkResult.Error(response.message(), response.body()))
            _leadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "login: ${errorObj}")
        } else {
            _leadLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }
}