package com.locus.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.gson.Gson
import com.locus.weather.databinding.ActivityViewBinding
import com.locus.weather.model.ForecastDayHrDataModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cityName = intent.getStringExtra("cityName").toString()
        val data = intent.getStringExtra("data").toString()
        val hrData = Gson().fromJson(data, ForecastDayHrDataModel::class.java)
        binding.toolbar.title = cityName
        setSupportActionBar(binding.toolbar)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
        binding.tempTv.text = hrData.temp_f.toString()
        binding.feelTv.text = getString(R.string.feel, hrData.feelslike_f)
        binding.nameTv.text = hrData.detailsModel?.text
        binding.humTv.text = getString(R.string.hum, hrData.humidity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}