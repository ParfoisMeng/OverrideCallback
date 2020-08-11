package com.parfoismeng.override.callback.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

class TransferFragment : Fragment() {
    private var listener: ((resultCode: Int, data: Intent?) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ACTIVITY_RESULT) {
            listener?.invoke(resultCode, data)
        }
    }

    fun startActivity4Callback(intent: Intent, listener: ((resultCode: Int, data: Intent?) -> Unit)? = null) {
        this.listener = listener
        startActivityForResult(intent, REQUEST_CODE_ACTIVITY_RESULT)
    }

    fun start4Callback(block: (fragment: Fragment, requestCode: Int) -> Unit, listener: ((resultCode: Int, data: Intent?) -> Unit)? = null) {
        block(this, REQUEST_CODE_ACTIVITY_RESULT)
        this.listener = listener
    }

    companion object {
        private const val REQUEST_CODE_ACTIVITY_RESULT = 10001
    }
}