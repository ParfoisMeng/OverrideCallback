package com.parfoismeng.override.callback

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.parfoismeng.override.callback.fragment.TransferFragment

fun FragmentActivity.start4Callback(block: (fragment: Fragment, requestCode: Int) -> Unit, callback: ((resultCode: Int, data: Intent?) -> Unit)? = null) {
    OverrideCallback(this).start4Callback(block = block, callback = callback)
}

fun FragmentActivity.start4Callback(intent: Intent, callback: ((resultCode: Int, data: Intent?) -> Unit)? = null) {
    OverrideCallback(this).start4Callback(intent = intent, callback = callback)
}

private class OverrideCallback constructor(activity: FragmentActivity) {
    companion object {
        private val TAG: String = OverrideCallback::class.java.simpleName
    }

    fun start4Callback(intent: Intent? = null, block: ((fragment: Fragment, requestCode: Int) -> Unit)? = null, callback: ((resultCode: Int, data: Intent?) -> Unit)? = null) {
        if (intent != null) {
            fragmentLazy?.get()?.startActivity4Callback(intent, callback)
        } else if (block != null) {
            fragmentLazy?.get()?.start4Callback(block, callback)
        }
    }

    private var fragmentLazy: Lazy<TransferFragment?>? = null

    init {
        fragmentLazy = getLazySingleton(activity.supportFragmentManager)
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

    @FunctionalInterface
    interface Lazy<V> {
        fun get(): V
    }
}