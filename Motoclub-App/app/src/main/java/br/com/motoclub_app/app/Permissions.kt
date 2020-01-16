package br.com.motoclub_app.app

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.motoclub_app.type.ActivityRequestCodeType
import javax.inject.Inject

class Permissions @Inject constructor(val context: Context) {

    var permissionsToRequest = mutableListOf<String>()

    fun addCameraPermission() {

        if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) return

        permissionsToRequest.add(Manifest.permission.CAMERA)
    }

    fun addWriteStoragePermission() {
        permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


    fun validateAndRequestPermission(activity: Activity) {

        val permissions = permissionsToRequest.filter { p ->
            if (ContextCompat.checkSelfPermission(context, p) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.shouldShowRequestPermissionRationale(activity, p)
            } else {
                false
            }

        }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                permissions.toTypedArray(),
                ActivityRequestCodeType.REQUEST_PERMISSION
            )
        }
    }
}