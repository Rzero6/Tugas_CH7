package com.example.tugasch7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasch7.databinding.ActivityMainBinding
import com.example.tugasch7.topic1.YTVideoActivity
import com.example.tugasch7.topic2.JSONActivity
import com.example.tugasch7.topic3.RetrofitActivity
import com.example.tugasch7.topic4.MVVMActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startActivity(Intent(this,YTVideoActivity::class.java))
        }
        binding.button2.setOnClickListener {
            startActivity(Intent(this,JSONActivity::class.java))
        }
        binding.button3.setOnClickListener {
            startActivity(Intent(this,RetrofitActivity::class.java))
        }
        binding.button4.setOnClickListener {
            startActivity(Intent(this,MVVMActivity::class.java))
        }
    }
}