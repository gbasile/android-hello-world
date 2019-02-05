package com.example.helloworld

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) =
    observe(lifecycleOwner, Observer { it?.let(observer) })