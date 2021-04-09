package com.example.tugasch7.topic1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasch7.databinding.ActivityYTVideoBinding

class YTVideoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityYTVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYTVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}