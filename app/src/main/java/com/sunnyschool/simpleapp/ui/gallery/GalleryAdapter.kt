package com.sunnyschool.simpleapp.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyschool.simpleapp.R
import com.sunnyschool.simpleapp.setImage

class GalleryAdapter(
    private val galleryData: MutableList<GalleryData>,
    val onClick : (GalleryData) -> (Unit)
) :
    RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(galleryData[position.coerceIn(0, galleryData.lastIndex)])
    }

    override fun getItemCount(): Int = galleryData.size

    inner class GalleryItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var mData: GalleryData? = null
        init{
            view.setOnClickListener {
                mData?.let { it1 -> onClick.invoke(it1) }
            }
        }
        fun bind(data: GalleryData) {
            mData = data
            val imageView: ImageView = view.findViewById(R.id.galleryImage)
            val textView: TextView = view.findViewById(R.id.artistNameText)
            view.setOnClickListener {
                onClick.invoke(data)
            }
            imageView.setImage(data.image)
            textView.text = data.artistName
        }
    }

}

