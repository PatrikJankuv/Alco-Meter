package com.jankuv.alcometer

class Shot(var drink: String, var volume: Int, var percentage: Int) {
    fun alcohol_content(): Int {
        return volume * percentage
    }

}