package com.parfoismeng.nooverridecallback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parfoismeng.nooverridecallbacklib.NoOverrideCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = javaClass.simpleName

        textView.apply {
            setOnClickListener {
                val testIntent = Intent().apply {
                    setClass(this@MainActivity, SecondActivity::class.java)
                    putExtra("DATA", "------ Call No.${++count} ------")
                }
                NoOverrideCallback.with(this@MainActivity)
                        .startActivity4Callback(testIntent, { resultCode, data ->
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
                        }, {
                            it?.printStackTrace()
                        })
            }
        }

        textViewDesc.text = ("初始状态，未使用过NoOverrideCallback\nfragment count = " + supportFragmentManager.fragments.size)

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
