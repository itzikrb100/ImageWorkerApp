package com.itzik.myimagesapp.Utils

import com.itzik.myimagesapp.ui.model.Image
import com.itzik.myimagesapp.repo.entities.ImageEntity

object Convert {

    fun convertImageEntityToImage(convertImage: ImageEntity): Image {
         return Image(convertImage.date, convertImage.url)
    }
}