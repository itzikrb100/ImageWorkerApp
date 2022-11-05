package com.itzik.myimagesapp.repo.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itzik.myimagesapp.repo.entities.ImageEntity


@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = true
)
abstract  class ImageDatabase : RoomDatabase() {
    abstract fun imageDao():ImageDao

    companion object {

        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ImageDatabase {
            Log.d("itzik-ImageDatabase", "build images db")
            return Room.databaseBuilder(
                context.applicationContext,
                ImageDatabase::class.java,
                "images_database"
            )
                .build()
        }
    }
}