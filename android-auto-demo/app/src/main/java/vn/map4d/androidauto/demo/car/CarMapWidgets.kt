package vn.map4d.androidauto.demo.car

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import vn.map4d.androidauto.demo.R
import vn.map4d.androidauto.map.Map4dCarMapObserver
import vn.map4d.androidauto.map.Map4dCarMapSurface
import vn.map4d.androidauto.map.widgets.LogoWidget

class CarMapWidgets : Map4dCarMapObserver {
    private lateinit var logoWidget: LogoWidget

    private lateinit var bitmap1: Bitmap
    private lateinit var bitmap2: Bitmap
    private val handler = Handler(Looper.getMainLooper())
    private val updateRunnable = object : Runnable {
        var counter: Int = 0
        var angle: Float = 0f
        override fun run() {
//            logoWidget.updateBitmap(if (counter++ % 2 == 0) bitmap2 else bitmap1)
            logoWidget.setRotation(angle)
            angle = (angle + 36f) % 360f
            handler.postDelayed(this, 1000)
        }
    }

    override fun onAttached(map4dCarMapSurface: Map4dCarMapSurface) {
        super.onAttached(map4dCarMapSurface)
        with(map4dCarMapSurface) {
            bitmap1 = BitmapFactory.decodeResource(carContext.resources, R.drawable.default_marker)
            bitmap2 = BitmapFactory.decodeResource(carContext.resources, R.drawable.ic_my_position_compass)
            logoWidget = LogoWidget(carContext)
            mapView.addWidget(logoWidget)
            handler.post(updateRunnable)
        }
    }

    override fun onDetached(map4dCarMapSurface: Map4dCarMapSurface) {
        super.onDetached(map4dCarMapSurface)
        handler.removeCallbacks(updateRunnable)
        with(map4dCarMapSurface) {
            mapView.removeWidget(logoWidget)
        }
    }
}