package com.caldremch.android.http

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.exec

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        HttpManager.post("").put(Any()).exec<Any> {  }
    }

    fun Go(view: View) {
        startActivity(Intent(this, HttpActivity::class.java))
    }
}