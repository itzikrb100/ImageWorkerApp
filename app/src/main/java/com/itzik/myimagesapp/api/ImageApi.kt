package com.itzik.myimagesapp.api

import com.itzik.myimagesapp.api.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {

    @GET("https://dog.ceo/api/breeds/image/random/{number}")
    suspend fun doGetListImages(@Path("number") number: Int): Response<ImageResponse>
}