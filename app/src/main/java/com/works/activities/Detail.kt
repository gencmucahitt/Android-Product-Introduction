package com.works.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.works.utils.UserLoginObject
import com.works.databinding.ActivityDetailBinding
import com.works.models.OrderData
import com.works.service.Service
import com.works.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail : AppCompatActivity() {

    private lateinit var bind:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        val name = intent.getStringExtra("Tittle")
        val price = intent.getStringExtra("Price")
        val description = intent.getStringExtra("Description")
        val image = intent.getStringExtra("İmage")
        val proid=intent.getStringExtra("Urunid")
        bind.textTittle2.text=name
        bind.txtPrices2.text=price+"₺"
        bind.txtDescription.text=description
        Glide.with(this@Detail).load(image).into(bind.imageDetail);

        val userid = UserLoginObject.userId!!
        bind.btnOrder.setOnClickListener {
            val service= ApiClient.getClient().create(Service::class.java)
            val dataService = service.orderGet(userid,proid!!)
            dataService.enqueue(object : Callback<OrderData>{
                override fun onResponse(call: Call<OrderData>, response: Response<OrderData>) {
                    if (response.isSuccessful){
                        val ord = response.body()
                        val durum = ord?.order?.get(0)?.durum
                        val message = ord?.order?.get(0)?.mesaj
                        if (durum == true){
                            Toast.makeText(this@Detail, ""+name +" "+message, Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@Detail, ""+message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                override fun onFailure(call: Call<OrderData>, t: Throwable) {
                    Log.d("onFailure Details", "onFailure: $t ")
                }

            })

        }





    }
}