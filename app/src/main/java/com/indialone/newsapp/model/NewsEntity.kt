package com.indialone.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsEntity {
    @Expose
    @SerializedName("articles")
    var articles: List<Articles>? = null

    @Expose
    @SerializedName("totalResults")
    var totalResults = 0

    @Expose
    @SerializedName("status")
    var status: String? = null
}