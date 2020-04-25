package com.airtel.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun log(s:String){
    Log.d("TAGS", "log: $s")
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}