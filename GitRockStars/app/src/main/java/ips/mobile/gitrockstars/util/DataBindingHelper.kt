package ips.mobile.gitrockstars.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeholder")
    fun loadImage(view: ImageView, url: String?, placeholder: Drawable) {
        if (url.isNullOrBlank()) {
            view.setImageDrawable(placeholder)
        } else {
            Picasso.get().load(url).error(placeholder).into(view)
        }
    }

}