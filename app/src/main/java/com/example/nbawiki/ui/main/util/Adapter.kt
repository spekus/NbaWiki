package com.example.nbawiki.ui.main.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object Adapter {

        @JvmStatic
        @BindingAdapter("android:src")
        fun bindImageFromUrl(view: ImageView, url : String) {
            Picasso
                .get()
                .load(url)
                .into(view)
        }

}