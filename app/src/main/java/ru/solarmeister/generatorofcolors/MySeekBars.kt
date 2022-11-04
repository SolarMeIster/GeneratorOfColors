package ru.solarmeister.generatorofcolors

import android.graphics.Color
import android.widget.SeekBar

class MySeekBars : SeekBar.OnSeekBarChangeListener {



    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    /*fun getColor(): Int {
        return Color.argb(
            alpha,
            red,
            green, blue
        )
    }*/
}