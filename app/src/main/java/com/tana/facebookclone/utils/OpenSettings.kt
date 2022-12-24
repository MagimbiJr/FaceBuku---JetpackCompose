package com.tana.facebookclone.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings


fun Context.openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        val uri = Uri.fromParts("package", packageName, null)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        data = uri
    }
    startActivity(intent)
}