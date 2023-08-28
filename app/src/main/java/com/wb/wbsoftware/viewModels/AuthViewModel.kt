package com.wb.wbsoftware.viewModels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.wbsoftware.models.auth.LoginRequest
import com.wb.wbsoftware.models.auth.LoginResponse
import com.wb.wbsoftware.network.NetworkResult
import com.wb.wbsoftware.viewModels.repositorys.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    val userRepositoryLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = userRepository.loginResponseLiveData

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch{
            userRepository.login(loginRequest)

        }
    }

    fun validateCredentials(email:String,password:String, isLogin:Boolean): Pair<Boolean,String>{
        var result=Pair(true,"")
        if (!isLogin && TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            result=Pair(false,"Please provide the credential")
        }
        else if (password.length<=2){
            result=Pair(false,"Password length should be getter than 2")

        }
        return result

    }


}