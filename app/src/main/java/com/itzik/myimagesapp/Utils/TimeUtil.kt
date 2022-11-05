package com.itzik.myimagesapp.Utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getCurrentDate(): String {
        return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    }

    fun getTime(): Long {
        return System.currentTimeMillis()
    }
}