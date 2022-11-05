package com.itzik.myimagesapp.repo

import android.content.Context
import android.util.Log
import com.itzik.myimagesapp.repo.db.ImageDao
import com.itzik.myimagesapp.repo.db.ImageDatabase
import com.itzik.myimagesapp.repo.entities.ImageEntity
import kotlinx.coroutines.flow.Flow

object ImagesRepo {

    private lateinit var imageDAO: ImageDao

    fun init(context: Context){
        imageDAO = ImageDatabase.getDatabase(context).imageDao()
        Log.d("itzik-ImagesRepo", "init Repo images")
    }

    fun getAllImages(): Flow<List<ImageEntity>> {
        return  imageDAO.getImages()
     }

    suspend fun addImages(images: List<ImageEntity>) {
         for (image in images) {
              imageDAO.addImage(image)
         }
     }

     suspend fun clearAllRepo() {
         imageDAO.deleteAllImages()
     }
}