package com.caldremch.android.http

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.caldremch.common.utils.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Go(view: View) {
        launch<HttpActivity>()
    }
}