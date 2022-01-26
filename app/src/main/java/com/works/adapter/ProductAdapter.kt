package com.works.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.works.models.BilgilerProduct
import com.works.databinding.ProductRowBinding.inflate

class ProductAdapter(private val context: Context,private val arrList : ArrayList<BilgilerProduct>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrList.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val bind = inflate(LayoutInflater.from(context),p2,false)
        val item = arrList.get(p0)
        bind.textTitle.text=item.productName
        bind.txtPrice.text=item.price +"₺"
        Glide.with(context).load(item.images?.get(0)?.normal).centerCrop().into(bind.productView)
        return bind.root
    }




}
