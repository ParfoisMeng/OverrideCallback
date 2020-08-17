package com.parfoismeng.OverrideCallback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parfoismeng.override.callback.start4Callback
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = javaClass.simpleName

        val testIntent = Intent().apply {
            setClass(this@MainActivity, SecondActivity::class.java)
            putExtra("DATA", "------ Call No.${++count} ------")
        }
        textView.setOnClickListener {
            start4Callback(
                    intent = testIntent,
                    callback = { resultCode, data ->
                        val resultCodeString = when (resultCode) {
                            Activity.RESULT_OK -> "RESULT_OK"
                            Activity.RESULT_CANCELED -> "RESULT_CANCELED"
                            Activity.RESULT_FIRST_USER -> "RESULT_FIRST_USER"
                            else -> "RESULT_CUSTOM_$resultCode"
                        }
                        textViewDesc.text = ("------ Back No.${count} ------" +
                                "\nresultCode = " + resultCodeString +
                                "\ndata = " + data?.getStringExtra("DATA") +
                                "\nfragment count = " + supportFragmentManager.fragments.size)
                    }
            )
        }
        textView4Func.setOnClickListener {
            start4Callback(
                    block = { fragment, requestCode ->
                        val intent = Intent().apply {
                            setClass(fragment.requireContext(), SecondActivity::class.java)
                            putExtra("DATA", "------ Call No.${++count} ------")
                        }
                        fragment.startActivityForResult(intent, requestCode)
                    },
                    callback = { resultCode, data ->
                        val resultCodeString = when (resultCode) {
                            Activity.RESULT_OK -> "RESULT_OK"
                            Activity.RESULT_CANCELED -> "RESULT_CANCELED"
                            Activity.RESULT_FIRST_USER -> "RESULT_FIRST_USER"
                            else -> "RESULT_CUSTOM_$resultCode"
                        }
                        textViewDesc.text = ("------ Back No.${count} ------" +
                                "\nresultCode = " + resultCodeString +
                                "\ndata = " + data?.getStringExtra("DATA") +
                                "\nfragment count = " + supportFragmentManager.fragments.size)
                    }
            )
        }

        textViewDesc.text = ("初始状态，未使用过OverrideCallback\nfragment count = " + supportFragmentManager.fragments.size)

        if (intent.getBooleanExtra("isSecondTestInitUtils", false)) {
            textView.apply {
                text = ("Close this page and go back to see the changes")
                setOnClickListener {
                    finish()
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//
//        textView.postDelayed({
//            textView.performClick()
//        }, 800)
//    }
}
