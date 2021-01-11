package com.example.architecturebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.network.MVVMView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentView = MVVMView()

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction().apply {

                setReorderingAllowed(true)
                add(R.id.fragment_view, fragmentView)

                commit()
            }
        }
    }
}