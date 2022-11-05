package com.itzik.myimagesapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itzik.myimagesapp.R
import com.itzik.myimagesapp.ui.model.Image
import kotlin.collections.ArrayList

class ImageAdapterList(items: ArrayList<Image>): RecyclerView.Adapter<ImageAdapterList.ItemsViewHolder>() {

    private var itemList: List<Image> = items

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val data = itemList[position]
        holder.bindView(data, position)
        Log.d("itzik-ImageAdapterList","onBindViewHolder: data = ${data}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ItemsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    fun setItemAdapter(items: List<Image>) {
        Log.d("itzik-ImageAdapterList","setItemAdapter")
        (itemList as ArrayList).addAll(items)
        notifyDataSetChanged()
    }

    inner class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Image, pos: Int) = with(itemView){
            Glide.with(this)
                .load(item.url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(findViewById(R.id.image_url))
        }
    }

}