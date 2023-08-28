package com.wb.wbsoftware.networks

import com.google.gson.GsonBuilder
import com.wb.wbsoftware.network.AllLeadAPI
import com.wb.wbsoftware.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkClient {

    @Singleton
    @Provides
    fun providesRetrofitBuilder() : Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(Constants.BASE_URL)
    }

    @Singleton
    @Provides
    fun provideOkHttpClint(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesAuthAPI(retrofitBuilder: Retrofit.Builder) : ApiInterface{
        return retrofitBuilder.build().create(ApiInterface::class.java)
    }
    /*@Singleton
    @Provides
    fun providesProductService(retrofit: Retrofit) : ProductApi{
        return retrofit.create(ProductApi::class.java)
    }*/

    /*@Singleton
    @Provides
    fun providesAuthRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }*/

    @Singleton
    @Provides
    fun providesAllLeadApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient):AllLeadAPI{
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(AllLeadAPI::class.java)
    }

    var gson = GsonBuilder()
        .setLenient()
        .create()

    var timeOut = 5 * 60
    private var client: OkHttpClient = OkHttpClient.Builder()//Builder()
        .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .build()

}