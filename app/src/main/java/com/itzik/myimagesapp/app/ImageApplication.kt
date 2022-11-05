package com.itzik.myimagesapp.app

import android.app.Application
import android.util.Log
import com.itzik.myimagesapp.businesslogiclayer.BusinessLogicImageService
import com.itzik.myimagesapp.businesslogiclayer.IBusinessLogicLayer
import com.itzik.myimagesapp.repo.ImagesRepo

class ImageApplication: Application() {

    private var busImageService: IBusinessLogicLayer? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("itzik-ImageApplication", "on create app  start business layer")
        ImagesRepo.init(applicationContext)
        busImageService = BusinessLogicImageService(context = applicationContext)
        busImageService?.startLayer()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}