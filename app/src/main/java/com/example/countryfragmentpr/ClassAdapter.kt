package com.example.countryfragmentpr

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.recyclerview.widget.ItemTouchHelper
import coil.transform.CircleCropTransformation
import com.example.countryfragmentpr.databinding.InLayoutBinding
import com.example.countryfragmentpr.model.CountryData
import java.util.*

class ClassAdapter(val context: Context, val listCountry:MutableList<CountryData>, val country:CountryTo):RecyclerView.Adapter<ClassAdapter.CountryHolder>() {

    class CountryHolder(itemview:InLayoutBinding):RecyclerView.ViewHolder(itemview.root){
        var name_country=itemview.nameCountryId
        var img_country=itemview.imgId
        var capital_country=itemview.capitalId
        var area_country=itemview.areaId
        var fav_country=itemview.favoriteId
        val linearlayout=itemview.linearlayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        var countryHolder=CountryHolder(InLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
        return countryHolder
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        var item=listCountry[position]
        holder.name_country.text=item.nameData
        holder.capital_country.text=item.capitalData
        holder.area_country.text=item.areaData
        holder.img_country.load(item.img_idData){
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
            transformations(CircleCropTransformation())
        }
        val anim = AnimationUtils.loadAnimation(context, R.anim.animation)
        holder.linearlayout.startAnimation(anim)
        if (item.position){
            holder.fav_country.setImageResource(R.drawable.img_fav)
        }
        else{
            holder.fav_country.setImageResource(R.drawable.new_list_button_image)
        }

        holder.fav_country.setOnClickListener {
            if (item.position) {
                holder.fav_country.setImageResource(R.drawable.new_list_button_image)
                item.position = false
                if (holder.fav_country.tag == 1) {
                    listCountry.removeAt(position)
                    notifyDataSetChanged()
                }
                return@setOnClickListener
            }
            holder.fav_country.setImageResource(R.drawable.img_fav)
            item.position = true
        }
            holder.itemView.setOnClickListener{
                country.itemclick(item)
            }
    }

    interface CountryTo{
        fun itemclick(countryData: CountryData)
    }
    fun itemmove(fromPosition: Int, toPosition: Int) {
        if(fromPosition < toPosition){
            for(i in fromPosition until toPosition){
                Collections.swap(listCountry, i, i+1)
            }
        }
        else{
            for(i in fromPosition downTo toPosition+1){
                Collections.swap(listCountry,i,i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun itemposition(position: Int) {
        listCountry.removeAt(position)
        notifyItemRemoved(position)
    }
}