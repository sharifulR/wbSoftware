package com.wb.wbsoftware.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager@Inject constructor(@ApplicationContext context: Context) {

    private var prefs= context.getSharedPreferences(Constants.PREFS_TOKEN_FILE,Context.MODE_PRIVATE)

    fun saveToken(token:String){
        val editor=prefs.edit()
        editor.putString(Constants.USER_TOKEN,token)
        editor.apply()
    }

    fun getToken():String?{
        return prefs.getString(Constants.USER_TOKEN,null)
    }

    var isLogin: Boolean?
        get() = prefs.getBoolean("is_login", false)
        set(isLogin) = prefs.edit().putBoolean("is_login", isLogin!!).apply()
}