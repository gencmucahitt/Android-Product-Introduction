package com.works.models


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("user")
    val user: List<UserReg?>?
)
    data class UserReg(
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("kullaniciId")
        val kullaniciId: String?,
        @SerializedName("mesaj")
        val mesaj: String?
    )
