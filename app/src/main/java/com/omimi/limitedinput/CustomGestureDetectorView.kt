package com.omimi.limitedinput

import android.content.Context
import android.util.AttributeSet
import android.view.View

class CustomGestureDetectorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){
    private lateinit var gestureCallback: GestureCallback

    //todo implement this to detect things and such

    fun setGestureCallback(gestureCallback: GestureCallback) {
        this.gestureCallback = gestureCallback
    }

}