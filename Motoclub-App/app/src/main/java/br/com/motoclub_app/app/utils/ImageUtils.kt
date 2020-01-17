package br.com.motoclub_app.app.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import br.com.motoclub_app.R
import com.bumptech.glide.Glide

class ImageUtils {

    companion object {

        var view: Context? = null

        fun loadImage(activity: Activity, imagePath: String?, imageView: ImageView) {

            this.view = activity
            load(imagePath, imageView)
        }

        fun loadImage(fragment: Fragment, imagePath: String?, imageView: ImageView) {
            this.view = fragment.context
            load(imagePath, imageView)
        }

        fun loadImage(context: Context, imagePath: String?, imageView: ImageView) {
            this.view = context
            load(imagePath, imageView)
        }

        private fun load(imagePath: String?, imageView: ImageView) {

            var image: Any? = null

            imagePath?.let {
                // Se estiver baixando de algum lugar mantém a string de url
                image = if (it.startsWith("http")) {
                    it
                } else {
                    Uri.parse(it)
                }
            }

            view?.apply {
                Glide
                    .with(this)
                    .load(image)
                    .centerCrop()
                    .placeholder(R.drawable.profile_picture)
                    .error(R.drawable.profile_picture)
                    .into(imageView)
            }
        }

        fun openImageViewer(contex: Context, imagePath: String) {

            val userIntent = Intent(Intent.ACTION_VIEW)
            userIntent.setDataAndType(Uri.parse(imagePath), "image/*")
            userIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            contex.startActivity(userIntent)
        }
    }
}