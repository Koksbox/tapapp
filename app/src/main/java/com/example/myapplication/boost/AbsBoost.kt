package com.example.myapplication.boost

abstract class AbsBoost {
    var price: Long = 0
    var title = ""
    var level = 0
    // TODO: add image

    abstract fun buy()
}