package com.sunnyschool.simpleapp

import android.content.Context
import android.widget.Toast

/**
 * Copyright Digitain 2020
 * Created by Narek Hayrapetyan on 16-11-2020.
 * E-Mail: narek.hayrapetyan@digitain.com
 */

fun Context.showToast (text:String) {
    Toast.makeText(this,
                   text,
                   Toast.LENGTH_SHORT).show()
}