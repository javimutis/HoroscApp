package com.javimutis.horoscapp.ui.core.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

// Esta clase detecta gestos de swipe en una vista (izquierda, derecha, arriba, abajo)
// Clase base que puede extenderse para reaccionar a gestos
open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 100 // Distancia mínima
        private const val SWIPE_VELOCITY_THRESHOLD = 100 // Velocidad mínima
    }

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    // Pasa el evento de toque al detector de gestos
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    // Clase interna que detecta tipo de swipe
    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        // Indica que se ha detectado un toque inicial
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // Detecta swipe horizontal o vertical
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1!!.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) onSwipeRight() else onSwipeLeft()
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) onSwipeBottom() else onSwipeTop()
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }
    }

    // Estas funciones se pueden sobrescribir en el Fragment para hacer cosas al hacer swipe
    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}
}