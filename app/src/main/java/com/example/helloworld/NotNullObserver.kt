package com.example.helloworld

import android.arch.lifecycle.Observer

class NotNullObserver<T>(private val onUnwrappedChange: (T) -> Unit): Observer<T> {
    override fun onChanged(t: T?) {
        t?.apply { onUnwrappedChange(this) }
    }
}