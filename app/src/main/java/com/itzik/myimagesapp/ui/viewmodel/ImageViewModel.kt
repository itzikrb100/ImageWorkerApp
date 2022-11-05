package com.itzik.myimagesapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itzik.myimagesapp.Utils.Convert
import com.itzik.myimagesapp.Utils.TimeUtil
import com.itzik.myimagesapp.repo.ImagesRepo
import com.itzik.myimagesapp.repo.entities.ImageEntity
import com.itzik.myimagesapp.ui.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ImageViewModel(val app: Application): AndroidViewModel(app) {

    private val imageList: LiveData<List<Image>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }

    fun fetchImageList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("itzik-ImageViewModel", "fetch image list")
            val test = listOf( ImageEntity(TimeUtil.getTime(), TimeUtil.getCurrentDate(), "urlxx") ,
                ImageEntity(TimeUtil.getTime(), TimeUtil.getCurrentDate(), "urlxx"))
            Log.d("itzik-ImageViewModel", "test image list = ${test}")
            ImagesRepo.addImages(test)
            ImagesRepo.getAllImages().collect{
                Log.d("itzik-ImageViewModel", "collect image list = ${it}")
                val transformImages = it.map { Convert.convertImageEntityToImage(it) } .toList()
                (imageList as MutableLiveData).postValue(transformImages)
            }




        }
    }

    fun getImageList(): LiveData<List<Image>> {
        return imageList
    }
}