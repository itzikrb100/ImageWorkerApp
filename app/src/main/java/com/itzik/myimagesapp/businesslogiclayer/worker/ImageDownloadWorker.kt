package com.itzik.myimagesapp.businesslogiclayer.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.itzik.myimagesapp.Utils.Consts
import com.itzik.myimagesapp.Utils.Consts.NUMBER_IMAGES
import com.itzik.myimagesapp.api.ApiClientService
import com.itzik.myimagesapp.api.ImageApi
import com.itzik.myimagesapp.api.model.ImageResponse


class ImageDownloadWorker(context: Context, parameters: WorkerParameters):
        CoroutineWorker(context, parameters){

    val api: ImageApi = ApiClientService(Consts.BASE_URL).createApi()

    override suspend fun doWork(): Result {
        // call from  Coroutine

       val result =  api.doGetListImages(NUMBER_IMAGES)
       if(result.isSuccessful) {
           val imagesResponse: ImageResponse? = result.body()

           if(!imagesResponse?.message.isNullOrEmpty()) {
               // insert to db

           }
       }
        Log.d("itzik- ImageDownloadWorker", "do work download image thread = ${Thread.currentThread().name}")
        return Result.success()
    }
}