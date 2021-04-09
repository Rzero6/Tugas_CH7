package com.example.tugasch7.topic2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasch7.R
import com.example.tugasch7.databinding.ActivityJSONBinding
import org.json.JSONArray
import org.json.JSONObject

class JSONActivity : AppCompatActivity() {
    private lateinit var binding : ActivityJSONBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJSONBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val jsonObject = JSONObject()
        val data = JSONObject()
        val listSocialMedia = JSONArray()
        val facebook = JSONObject()
        val instagram = JSONObject()
        val whatsapp = JSONObject()
        jsonObject.put("nama","Reynold Kunarto")
        jsonObject.put("data",data)
        data.put("umur",22)
        data.put("single",true)
        data.put("list_social_media",listSocialMedia)
        facebook.put("social_media","facebook")
        instagram.put("social_media","instagram")
        whatsapp.put("social_media","whatsapp")
        listSocialMedia.put(facebook)
        listSocialMedia.put(instagram)
        listSocialMedia.put(whatsapp)
        val nama = jsonObject.getString("nama")
        val biodata = jsonObject.getJSONObject("data")
        val umur = biodata.getInt("umur")
        val single = biodata.getBoolean("single")
        val socialMedia = biodata.getJSONArray("list_social_media")
        binding.textView2.text="Nama : "+nama+"\n"
        binding.textView2.append("Umur : "+umur.toString()+"\n")
        binding.textView2.append("Single : "+single.toString()+"\n")
        binding.textView2.append("Social Media : ")
        for (i in 0 until socialMedia.length()){
            val isi = socialMedia.getJSONObject(i)
            val namaSocialMedia = isi.getString("social_media")
            binding.textView2.append(namaSocialMedia+"\n")
        }

    }
}