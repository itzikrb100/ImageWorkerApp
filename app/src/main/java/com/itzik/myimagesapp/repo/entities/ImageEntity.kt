package com.itzik.myimagesapp.repo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images")
data class ImageEntity(
    @ColumnInfo(name = "timeStamp")
    val timeStamp: Long,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "url")
     val url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
