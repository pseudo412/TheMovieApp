package com.example.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCountryVO(
    @SerializedName("iso_3166_1")
    val isoName: String,

    @SerializedName("name")
    val name: String?,
)