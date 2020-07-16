package com.jankuv.alcometer

class Shot(var drink: String, var volume: Double, var percentage: Int) {
    fun alcohol_content(): Double {
        return volume * percentage
    }

}