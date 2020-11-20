package com.sunnyschool.simpleapp.ui.gallery

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.sunnyschool.simpleapp.R
import com.sunnyschool.simpleapp.setImage

class ImageDialog(private val data: GalleryData, val action: (actionDelete: Boolean) -> (Unit) ) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        view.findViewById<ImageView>(R.id.image).setImage(data.image)
        view.findViewById<ImageButton>(R.id.btn_delete).apply {
            setOnClickListener {
                action.invoke(true)
                dismiss()
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        dialog?.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
//    }

    companion object {
        const val TAG = "ImageDialog"
        fun newInstance(data: GalleryData, action: (actionDelete: Boolean) -> (Unit))
                = ImageDialog(data, action)
    }

}