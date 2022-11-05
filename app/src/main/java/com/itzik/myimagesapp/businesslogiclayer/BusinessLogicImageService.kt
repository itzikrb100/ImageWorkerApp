package com.itzik.myimagesapp.businesslogiclayer

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import com.itzik.myimagesapp.businesslogiclayer.worker.ImageDownloadWorker
import com.itzik.myimagesapp.businesslogiclayer.worker.ZipArchiveWorker
import java.util.concurrent.TimeUnit


class BusinessLogicImageService(private val context: Context): IBusinessLogicLayer {

     companion object {
          private  val DOWNLOAD_IMAGE_TAG = "download_daily_image"
          private  val ZIP_ARCHIVE_IMAGE_TAG = "zip_archive_image"
      }

    override fun startLayer() {
       var isRunning =  isWorkersScheduled(DOWNLOAD_IMAGE_TAG)
        Log.d("itzik-BusImageService", "startLayer check download image work is running = $isRunning")
        if(!isRunning) {

            val imageDownloadWorkBuilder = PeriodicWorkRequest.Builder(
                ImageDownloadWorker::class.java, 2, TimeUnit.HOURS)
                   .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresCharging(true).build())
            val imageDownloadWork = imageDownloadWorkBuilder.build()
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(DOWNLOAD_IMAGE_TAG, ExistingPeriodicWorkPolicy.KEEP, imageDownloadWork)
        }

        isRunning = isWorkersScheduled(ZIP_ARCHIVE_IMAGE_TAG)
        Log.d("itzik-BusImageService", "startLayer check zip work is running = $isRunning")
        if(!isRunning) {
            val zipArchiveImageWorkBuilder = PeriodicWorkRequest.Builder(
                ZipArchiveWorker::class.java, 48, TimeUnit.HOURS)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresCharging(true).build())
            val zipWork = zipArchiveImageWorkBuilder.build()
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(ZIP_ARCHIVE_IMAGE_TAG, ExistingPeriodicWorkPolicy.KEEP, zipWork)

        }


    }



     private fun isWorkersScheduled(tagWorker: String): Boolean {
        val statuses: LiveData<List<WorkInfo>> = WorkManager.getInstance(context)
                      .getWorkInfosByTagLiveData(tagWorker)

        if(statuses.value == null) return false
        val list: List<WorkInfo> = statuses.value as List<WorkInfo>
        Log.d("itzik-BusImageService","work info = ${list}")
        for(info in list) {
           if(info.state == WorkInfo.State.ENQUEUED ||
               info.state == WorkInfo.State.RUNNING ||
               info.state == WorkInfo.State.SUCCEEDED) {
               return true
           }
        }
        return false
    }
}