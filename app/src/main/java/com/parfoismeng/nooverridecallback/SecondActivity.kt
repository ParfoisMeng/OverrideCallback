package com.parfoismeng.nooverridecallback

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.apply {
            text = ("setResult")
            setOnClickListener {
                setResult(Activity.RESULT_OK, intent.apply {
                    putExtra("DATA", "我是第二页setResult#RESULT_OK的数据")
                })
                finish()
            }
        }
        textView2.text = ("intent data = " + intent?.getStringExtra("DATA") +
                "\nfragment count = " + supportFragmentManager.fragments.size)
    }

    override fun onResume() {
        super.onResume()

        textView.postDelayed({
            textView.performClick()
        }, 800)
    }

    override fun onBackPressed() {
        intent.apply {
            putExtra("DATA", "我是第二页setResult#RESULT_CANCELED的数据")
        }
        super.onBackPressed()
    }
}
