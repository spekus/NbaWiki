package com.example.nbawiki.ui.main.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object Adapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun bindImageFromUrl(view: ImageView, url: String?) {
        val formattedUrl = formatImageUrl(url)

        Picasso
            .get()
            .load(formattedUrl)
            .into(view)
    }


    private fun formatImageUrl(image: String?): String? {
        // Picasso can not handle blank url string, it can handle null
        if (image?.trim()?.isBlank() != false) {
            return null
        }
        return image
    }

}