package com.gcdominium

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    fun validate(activity: Activity, requestCode: Int,
                 vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            val ok = ContextCompat.checkSelfPermission(activity, permission) ==
                    PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) return true

        val newP = arrayOfNulls<String>(list.size)
        list.toArray(newP)
        ActivityCompat.requestPermissions(activity, newP, requestCode)
        return false
    }
}