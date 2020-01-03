package com.parfoismeng.nooverridecallbacklib.callback

import android.content.Intent

interface ActivityResultListener {
    fun onActivityResult(resultCode: Int, data: Intent?)
    fun onFailed(throwable: Throwable?)
}