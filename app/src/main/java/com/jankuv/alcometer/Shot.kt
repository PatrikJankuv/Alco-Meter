package com.jankuv.alcometer

import java.time.LocalDateTime

data class Shot(var drink: String, var volume: Double, var percentage: Int, var time: LocalDateTime) {
    fun alcohol_content(): Double {
        return volume * percentage
    }

}