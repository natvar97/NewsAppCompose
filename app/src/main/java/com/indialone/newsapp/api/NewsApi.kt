package com.indialone.newsapp.api

import com.indialone.newsapp.model.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsEntity

    @GET("everything")
    suspend fun getNewsEverything(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): NewsEntity

    @GET("top-headlines")
    suspend fun getNewsTopHeadlinesBySources(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsEntity

    @GET("everything")
    suspend fun getNewsEverythingQuery(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsEntity


    @GET("everything")
    suspend fun getNewsEverythingQueryTo(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("to") to : String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsEntity

}