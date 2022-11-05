package ru.solarmeister.generatorofcolors.viewmodel

import android.graphics.Color
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.solarmeister.generatorofcolors.R
import ru.solarmeister.generatorofcolors.model.Colors
import kotlin.random.Random

class ColorViewModel : ViewModel() {

    // цвета
    private val _colors = MutableLiveData<Colors>()
    val colors: LiveData<Colors> = _colors

    private var red = 0
    private var green = 0
    private var blue = 0
    private var alpha = 0

    fun generatorColor(): Int {
        alpha = Random.nextInt(0, 256)
        red = Random.nextInt(0, 256)
        green = Random.nextInt(0, 256)
        blue = Random.nextInt(0, 256)
        setColors(alpha, red, green, blue)
        return Color.argb(
            _colors.value!!.alpha,
            _colors.value!!.red,
            _colors.value!!.green,
            _colors.value!!.blue
        )
    }

    fun createOwnColor(seekBar: SeekBar?, progress: Int): Int {
        when (seekBar?.id) {
            R.id.alphaSeekBar -> alpha = progress
            R.id.redSeekBar -> red = progress
            R.id.greenSeekBar -> green = progress
            R.id.blueSeekBar -> blue = progress
        }
        setColors(alpha, red, green, blue)
        return Color.argb(
            _colors.value!!.alpha,
            _colors.value!!.red,
            _colors.value!!.green,
            _colors.value!!.blue
        )
    }

    private fun setColors(alpha: Int, red: Int, green: Int, blue: Int) {
        _colors.value = Colors(alpha, red, green, blue)
    }


}