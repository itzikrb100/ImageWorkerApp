package com.itzik.myimagesapp.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itzik.myimagesapp.repo.entities.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImage(image: ImageEntity)

    @Query("SELECT * FROM images ORDER BY timeStamp DESC")
    fun getImages(): Flow<List<ImageEntity>>

    @Query("DELETE FROM images")
    suspend fun deleteAllImages()
}