package com.parfoismeng.nooverridecallbacklib

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.parfoismeng.nooverridecallbacklib.callback.ActivityResultListener
import com.parfoismeng.nooverridecallbacklib.other.ActivityResultException

class TransferFragment : Fragment() {
    private var listener: ActivityResultListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ACTIVITY_RESULT) {
            listener?.onActivityResult(resultCode, data)
        } else {
            listener?.onFailed(ActivityResultException("requestCode mismatch"))
        }
    }

    fun startActivity4Callback(intent: Intent, listener: ActivityResultListener?) {
        this.listener = listener
        startActivityForResult(intent, REQUEST_CODE_ACTIVITY_RESULT)
    }

    companion object {
        private const val REQUEST_CODE_ACTIVITY_RESULT = 10001
    }
}