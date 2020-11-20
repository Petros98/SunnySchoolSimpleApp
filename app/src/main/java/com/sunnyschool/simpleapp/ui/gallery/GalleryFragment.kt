package com.sunnyschool.simpleapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sunnyschool.simpleapp.R
import kotlinx.coroutines.*
import kotlin.random.Random

/**
 * Copyright Digitain 2020
 * Created by Narek Hayrapetyan on 16-11-2020.
 * E-Mail: narek.hayrapetyan@digitain.com
 */
class GalleryFragment : Fragment() {
    private val galleryData: MutableList<GalleryData> by lazy {
        mutableListOf()
    }

    private lateinit var rv: RecyclerView
    private var isGrid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.recylerViewGallery)
        val adapter = GalleryAdapter(galleryData){
            openImageDialog(it)
        }
        rv.apply {
            layoutManager = LinearLayoutManager(rv.context,LinearLayoutManager.VERTICAL,false)
            addItemDecoration(ImageItemDecoration(8))
            this.adapter = adapter
        }
        lifecycleScope.launchWhenStarted {
            startAdding(adapter)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_grid){
            changeType(isGrid, rv)
            if (isGrid){
                item.setIcon(R.drawable.ic_grid)
            }
            else{
                item.setIcon(R.drawable.ic_linear)
            }
            isGrid = !isGrid
        }
        return true
    }

    private fun changeType(isGrid: Boolean, rv: RecyclerView){
        if (isGrid){
            rv.layoutManager = LinearLayoutManager(rv.context,LinearLayoutManager.VERTICAL,false)
        }
        else
            rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun openImageDialog(data: GalleryData){
        ImageDialog.newInstance(data){
            if (it){
                val position = galleryData.indexOf(data)
                galleryData.removeAt(position)
                rv.adapter?.notifyItemRemoved(position)
            }
        }.show(childFragmentManager, ImageDialog.TAG)
    }

    private suspend fun startAdding(adapter: GalleryAdapter){
        for (i in galleryData.lastIndex..100){
            galleryData.add(GalleryData("https://picsum.photos/200/1${Random.nextInt(30,99)}","artist $i"))
            adapter.notifyItemInserted(galleryData.lastIndex)
            delay(300)
        }
    }
}