package com.parfoismeng.nooverridecallback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parfoismeng.nooverridecallbacklib.NoOverrideCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.apply {
            text = ("startActivity4Callback")
            setOnClickListener {
                NoOverrideCallback(this@MainActivity).startActivity4Callback(
                    Intent().apply {
                        setClass(this@MainActivity, SecondActivity::class.java)
                        putExtra("DATA", "------ 第${count}次 ------")
                    },
                    { resultCode, data ->
                        textView2.text = ("------ 第${count++}次 ------" +
                                "\nRESULT_OK : " + (resultCode == Activity.RESULT_OK) +
                                "\ndata = " + data?.getStringExtra("DATA") +
                                "\nfragment count = " + supportFragmentManager.fragments.size)
                    },
                    {
                        it?.printStackTrace()
                    })
            }
        }
    }

    override fun onResume() {
        super.onResume()

        textView.postDelayed({
            textView.performClick()
        }, 800)
    }
}
