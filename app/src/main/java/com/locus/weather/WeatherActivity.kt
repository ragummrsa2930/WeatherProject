package com.locus.weather

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.locus.weather.network.response.Status
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.locus.weather.databinding.ActivityWeatherBinding
import com.locus.weather.model.ResponseModel
import com.locus.weather.adapter.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val daggerVM: DaggerVM by viewModels()
    lateinit var cityName: String
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cityName = intent.getStringExtra("cityName").toString()
        binding.toolbar.title = cityName
        setSupportActionBar(binding.toolbar)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }

        daggerVM.getWeatherInfo(cityName){
            when(it.status){
                Status.LOADING ->{
                    showLoading()
                }
                Status.SUCCESS ->{
                    cancelLoading()
                    loadImage(it.data)
                }
                Status.ERROR ->{
                    cancelLoading()
                    if(it.exception?.statusCode == 400){
                        showErrorPopup(it.exception.errorMessage)
                    }else{
                        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun cancelLoading() {
        if (alertDialog != null) {
            alertDialog?.cancel()
        }

    }

    private fun showLoading() {
        if (alertDialog != null && alertDialog?.isShowing == true) {
            return
        }
        alertDialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.loading_dialog)
            .create().apply {
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
        alertDialog?.show()
    }


    private fun loadImage(data: ResponseModel?) {
        if(data?.forecast != null && data.forecast.forcasetDayList.isNotEmpty()){
            val adapter = RecyclerViewAdapter(data.forecast.forcasetDayList[0].hourDataList)
            binding.recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recycler.adapter = adapter
            adapter.onItemClick = { hrDataModel ->
                val intent = Intent(this, WeatherDetailActivity::class.java)
                intent.putExtra("cityName", cityName)
                intent.putExtra("data", Gson().toJson(hrDataModel))
                startActivity(intent)
            }
        }else{
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
        }
    }

    private fun showErrorPopup(data: String) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                    finish()
                })
        }
        builder.setMessage(data)
        builder.create()
        builder.show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}