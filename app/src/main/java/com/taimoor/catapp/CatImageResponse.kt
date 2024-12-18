package com.taimoor.catapp

import com.google.gson.annotations.SerializedName

data class CatImageResponse(

    @SerializedName("id") val id: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?
)
