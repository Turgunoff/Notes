package com.uz.notes.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS


/**
 * Created by Eldor Turgunov on 04.09.2022.
 * Notes
 * eldorturgunov777@gmail.com
 */

fun View.hideKeyboard() = (context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
    .hideSoftInputFromWindow(windowToken, HIDE_NOT_ALWAYS)