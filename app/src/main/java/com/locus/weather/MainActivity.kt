package com.locus.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.locus.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cityname.doAfterTextChanged {
            binding.lookupBtn.isEnabled = false
            if(it != null && it.isNotEmpty()){
                binding.lookupBtn.isEnabled = true
            }
        }
        binding.lookupBtn.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("cityName", binding.cityname.text.toString())
            binding.cityname.setText("")
            startActivity(intent)
        }
    }
}