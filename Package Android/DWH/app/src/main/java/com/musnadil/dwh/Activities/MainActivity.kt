package com.musnadil.dwh.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.musnadil.dwh.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionbar = supportActionBar
        actionbar?.hide()
        prediksi()

    }

    override fun onStart() {
        etDay.text = null
        etMonth.text = null
        etYear.text = null
        etDay.requestFocus()
        tvError.text = null
        super.onStart()
    }
    fun prediksi(){
        btnPrediksi.setOnClickListener {
            if(etDay.text.toString().isEmpty()){
                tvError.text = "Tanggal tidak boleh kosong"
                etDay.requestFocus()
            }else if(etMonth.text.toString().isEmpty()){
                tvError.text = "Bulan tidak boleh kosong"
                etMonth.requestFocus()
            }else if (etYear.text.toString().isEmpty()){
                tvError.text = "Tahun tidak boleh kosong"
                etYear.requestFocus()
            }else if(etYear.text.toString().length <4){
                tvError.text = "Tahun diisi dengan 4 angka"
                etYear.requestFocus()
                etYear.text = null
            }else if(etYear.text.toString().toInt()<=2003 || etYear.text.toString().toInt()>=2040){
                tvError.text = "Prediksi tidak terjangkau"
                etYear.requestFocus()
                etYear.text = null
            }else if(etDay.text.toString().toInt()==0 ||etDay.text.toString().toInt()>31 ){
                tvError.text = "Prediksi tidak terjangkau"
                etDay.requestFocus()
                etDay.text=null
            }else if(etMonth.text.toString().toInt()==0 || etMonth.text.toString().toInt()>12){
                tvError.text = "Prediksi tidak terjangkau"
                etMonth.requestFocus()
                etMonth.text = null
            }
            else{
                tvError.text = null
                perhitungan()
            }
        }
    }
    fun perhitungan(){
        var tanggal = etDay.text.toString()
        var bulan = etMonth.text.toString()
        var tahun = etYear.text.toString()


        var date:String = (tanggal+bulan+tahun)
        var A = Integer.parseInt(date)
        var m = -0.0003758148911106377
        var c = 37018.21500930723



        var pred = (m*A)+c
        var hasil = pred.toInt().toString()

        val intent = Intent(this,HasilActivity::class.java)
        intent.putExtra("tanggal",tanggal)
        intent.putExtra("bulan",bulan)
        intent.putExtra("tahun",tahun)
        intent.putExtra("hasil",hasil)

        startActivity(intent)

    }
}