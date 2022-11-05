package com.itzik.myimagesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itzik.myimagesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}