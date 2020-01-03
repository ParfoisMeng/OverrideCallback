package com.parfoismeng.nooverridecallbacklib

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.parfoismeng.nooverridecallbacklib.callback.ActivityResultListener


/**
 * author : ParfoisMeng
 * time   : 2020-01-02
 * desc   : ...
 */
class NoOverrideCallback private constructor(activity: FragmentActivity) {
    private var fragmentLazy: Lazy<TransferFragment?>? = null

    init {
        fragmentLazy = getLazySingleton(activity.supportFragmentManager)
    }

    fun startActivity4Callback(
            intent: Intent,
            onActivityResultMethod: ((resultCode: Int, data: Intent?) -> Unit)? = null,
            onFailedMethod: ((throwable: Throwable?) -> Unit)? = null
    ) {
        startActivity4Callback(intent, object : ActivityResultListener {
            override fun onActivityResult(resultCode: Int, data: Intent?) {
                onActivityResultMethod?.invoke(resultCode, data)
            }

            override fun onFailed(throwable: Throwable?) {
                onFailedMethod?.invoke(throwable)
            }
        })
    }

    @JvmOverloads
    fun startActivity4Callback(intent: Intent, listener: ActivityResultListener? = null) {
        fragmentLazy?.get()?.startActivity4Callback(intent, listener)
    }

    private fun getLazySingleton(fragmentManager: FragmentManager): Lazy<TransferFragment?> {
        return object : Lazy<TransferFragment?> {
            private var transferFragment: TransferFragment? = null

            @Synchronized
            override fun get(): TransferFragment? {
                if (transferFragment == null) {
                    transferFragment = getTransferFragment(fragmentManager)
                }
                return transferFragment
            }
        }
    }

    private fun getTransferFragment(fragmentManager: FragmentManager): TransferFragment? {
        var transferFragment: TransferFragment? = findTransferFragment(fragmentManager)
        val isNewInstance = transferFragment == null
        if (isNewInstance) {
            transferFragment = TransferFragment()
            fragmentManager.beginTransaction().add(transferFragment, TAG).commitNow()
        }
        return transferFragment
    }

    private fun findTransferFragment(fragmentManager: FragmentManager): TransferFragment? {
        return fragmentManager.findFragmentByTag(TAG) as TransferFragment?
    }

    companion object {
        private val TAG: String = NoOverrideCallback::class.java.simpleName

        @JvmStatic
        fun with(activity: FragmentActivity) = NoOverrideCallback(activity)
    }

    @FunctionalInterface
    interface Lazy<V> {
        fun get(): V
    }
}