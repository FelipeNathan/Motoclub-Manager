package br.com.motoclub_app.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import br.com.motoclub_app.R
import br.com.motoclub_app.app.DateUtils
import br.com.motoclub_app.type.ActivityRequestCodeType
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.text.DateFormat
import java.util.*

class BottomNavigationFragment : BottomSheetDialogFragment() {

    var listener: (imgUri: Uri?) -> Unit = {}
    var photoUri: Uri? = null

    private val packageManager by lazy {
        context?.packageManager!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.nav_bottom_layout, container, false)

        dialog?.setOnShowListener {
            val d = it as BottomSheetDialog
            val layout = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            layout.layoutParams.height = 1000
            BottomSheetBehavior.from(layout).peekHeight = 1000
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.gallery_icon_dialog).setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, ActivityRequestCodeType.SELECT_IMAGE)
        }

        val camera = view.findViewById<LinearLayout>(R.id.camera_icon_dialog)

        if (shouldHideCameraIcon()) {
            camera.visibility = View.GONE
        } else {
            camera.setOnClickListener {
                openCamera()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {

                ActivityRequestCodeType.SELECT_IMAGE -> {

                    if (data == null) return
                    this.listener(data.data)
                }

                ActivityRequestCodeType.REQUEST_IMAGE_CAPTURE -> {
                    this.listener(photoUri)
                }
            }
        }
    }

    private fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        intent.resolveActivity(packageManager)?.also {

            val photoFile: File? = try {
                createImageFile()
            } catch (ex: Exception) {
                null
            }

            photoFile?.also {
                photoUri = FileProvider.getUriForFile(context!!, "br.com.motoclub_app.fileprovider", it)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, ActivityRequestCodeType.REQUEST_IMAGE_CAPTURE)
            }

        }
    }

    private fun shouldHideCameraIcon(): Boolean {

        return !packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY) || ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_DENIED
    }

    private fun createImageFile(): File {

        val timeStamp: String = DateUtils.calendarToImageName(Calendar.getInstance())
        val storageDir = context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    fun onImageSelectedListener(listener: (imgUri: Uri?) -> Unit) {
        this.listener = listener
    }

}