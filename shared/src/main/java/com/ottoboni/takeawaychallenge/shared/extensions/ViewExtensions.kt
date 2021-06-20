package com.ottoboni.takeawaychallenge.shared.extensions

import android.view.LayoutInflater
import android.view.View

val View.layoutInflater get(): LayoutInflater = LayoutInflater.from(context)
