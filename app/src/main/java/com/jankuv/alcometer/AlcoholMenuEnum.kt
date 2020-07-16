package com.jankuv.alcometer

enum class AlcoholMenuEnum(val percentage: Int, val volume: Double) {
    BEER10(4, 0.5),
    BEER12(5, 0.5),
    VODKA(40, 0.05),
    WINE(12, 0.2),
    TATRA_TEA(72, 0.05),
    GIN(35, 0.05),
    SPIRIT(52, 0.05)
}