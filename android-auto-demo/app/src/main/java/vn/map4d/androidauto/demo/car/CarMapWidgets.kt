package vn.map4d.androidauto.demo.car

import vn.map4d.androidauto.map.Map4dCarMapObserver
import vn.map4d.androidauto.map.Map4dCarMapSurface
import vn.map4d.androidauto.map.widgets.LogoWidget

class CarMapWidgets : Map4dCarMapObserver {
    private lateinit var logoWidget: LogoWidget

    override fun onAttached(map4dCarMapSurface: Map4dCarMapSurface) {
        super.onAttached(map4dCarMapSurface)
        with(map4dCarMapSurface) {
            logoWidget = LogoWidget(carContext)
            mapView.addWidget(logoWidget)
        }
    }

    override fun onDetached(map4dCarMapSurface: Map4dCarMapSurface) {
        super.onDetached(map4dCarMapSurface)
        with(map4dCarMapSurface) {
            mapView.removeWidget(logoWidget)
        }
    }
}