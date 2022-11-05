package com.itzik.myimagesapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryViewModel: ViewModelProvider.Factory {

    private var application: Application? = null

    constructor(application: Application?){
        this.application = application
    }

    override fun <T:ViewModel> create(modelClass: Class<T>):T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageViewModel(application!!) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}