package com.works.service

import com.works.models.OrderData
import com.works.models.ProductData
import com.works.models.UserLogin
import com.works.models.UserRegister
import com.works.utils.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("userLogin.php")
    fun userLogin(
        @Query("userEmail") userEmail:String,
        @Query("userPass") userPass:String,
        @Query("ref") ref:String = ApiClient.ref,
        @Query("face") face:String ="no"
    ): Call<UserLogin>

    @GET("userRegister.php")
    fun userRegister(
        @Query("userName") userName:String,
        @Query("userSurname") userSurname:String ,
        @Query("userPhone") userPhone:String ,
        @Query("userMail") userMail:String ,
        @Query("userPass") userPass:String,
        @Query("ref") ref:String = ApiClient.ref,
    ):Call<UserRegister>

    @GET("product.php")
    fun productGet(
        @Query("start") start:String="0",
        @Query("ref") ref:String = ApiClient.ref,
    ):Call<ProductData>

    @GET("orderForm.php")
    fun orderGet(
        @Query("customerId") customerId:String,
        @Query("productId") productId:String,
        @Query("html") html:String="12",
        @Query("ref") ref:String = ApiClient.ref,
    ):Call<OrderData>



}