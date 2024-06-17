package com.livmas.ui

import android.text.Editable
import android.text.TextWatcher

class MyTextWatcher(val callback: (String) -> Unit): TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        p0?.let {
            if (it.isCirillic())
                callback(p0.toString())
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    private fun CharSequence.isCirillic(): Boolean {
        forEach {
            if (it.lowercaseChar() !in 'а'..'я')
                return false
        }
        return true
    }
}