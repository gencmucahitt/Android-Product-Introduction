package com.works.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.works.adapter.ProductAdapter
import com.works.databinding.ActivityProductBinding
import com.works.models.BilgilerProduct
import com.works.models.ProductData
import com.works.service.Service
import com.works.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Product : AppCompatActivity() {
    private lateinit var bind:ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityProductBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()
        var service = ApiClient.getClient().create(Service::class.java)
        val call =service.productGet()
        call.enqueue(object :Callback<ProductData>{
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful){
                    val pro = response.body()
                    if (pro != null ){
                        val datalist=pro.products!!.get(0)!!.bilgiler!! as ArrayList<BilgilerProduct>
                        val adapter = ProductAdapter(this@Product, datalist)
                        bind.productListView.adapter=adapter

                        bind.productListView.setOnItemClickListener { adapterView, view, i, l ->
                            val title =datalist.get(i).productName
                            val price =datalist.get(i).price
                            val description = datalist.get(i).description
                            val image = datalist.get(i).images?.get(0)?.normal
                            val proid = datalist.get(i).productId


                            val i = Intent(this@Product, Detail::class.java)
                            i.putExtra("Tittle",title)
                            i.putExtra("Price",price)
                            i.putExtra("Description",description)
                            i.putExtra("İmage",image)
                            i.putExtra("Urunid",proid)
                            startActivity(i)

                        }

                    }

                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("onFailure Product", "onFailure: $t ")
            }


        })


    }

}