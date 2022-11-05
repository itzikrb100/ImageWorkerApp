package com.itzik.myimagesapp.businesslogiclayer.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ZipArchiveWorker(context: Context, parameters: WorkerParameters):
    CoroutineWorker(context, parameters){


    override suspend fun doWork(): Result {
        Log.d("itzik- ZipArchiveWorker", "do work zip image, thread = ${Thread.currentThread().name}")
        // need to implement: pass all records from db to file and zipped
        return Result.success()
    }
}