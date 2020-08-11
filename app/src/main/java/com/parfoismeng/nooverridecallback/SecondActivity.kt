package com.parfoismeng.OverrideCallback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parfoismeng.override.callback.start4Callback
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title = javaClass.simpleName

        val data = intent?.getStringExtra("DATA")
        textViewDesc.text = (data +
                "\n此页面未使用过OverrideCallback" +
                "\nfragment count = " + supportFragmentManager.fragments.size)
        textViewTestInitUtils.setOnClickListener {
            start4Callback(Intent().apply {
                setClass(this@SecondActivity, MainActivity::class.java)
                putExtra("isSecondTestInitUtils", true)
            })

            textViewDesc.text = (data +
                    "\n此页面已使用OverrideCallback" +
                    "\nfragment count = " + supportFragmentManager.fragments.size)
        }

        textView1.setOnClickListener {
            setResult(Activity.RESULT_OK, intent.apply {
                putExtra("DATA", "我是RESULT_OK的数据")
            })
            finish()
        }
        textView2.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, intent.apply {
                putExtra("DATA", "我是RESULT_CANCELED的数据")
            })
            finish()
        }
        textView3.setOnClickListener {
            setResult(Activity.RESULT_FIRST_USER, intent.apply {
                putExtra("DATA", "我是RESULT_FIRST_USER的数据")
            })
            finish()
        }
        textView4.setOnClickListener {
            setResult(12345, intent.apply {
                putExtra("DATA", "我是RESULT_CUSTOM的数据")
            })
            finish()
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
