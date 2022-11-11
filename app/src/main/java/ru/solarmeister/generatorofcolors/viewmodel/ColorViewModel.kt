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

    private val variations: Array<String> = arrayOf("Случайный цвет", "Свой цвет")

    private var red = 0
    private var green = 0
    private var blue = 0

    init {
        setColors(red, green, blue, variations)
    }

    fun generatorColor(): Int {
        red = Random.nextInt(0, 256)
        green = Random.nextInt(0, 256)
        blue = Random.nextInt(0, 256)
        setColors(red, green, blue)
        return Color.argb(255,
            _colors.value!!.red,
            _colors.value!!.green,
            _colors.value!!.blue
        )
    }

    fun convertToHex(): String {
        var hexString = red.toString(16)
        hexString += green.toString(16)
        hexString += blue.toString(16)
        return hexString
    }

    fun convertToHex(red: Int, green: Int, blue: Int): String {
        var hexString = red.toString(16)
        hexString += green.toString(16)
        hexString += blue.toString(16)
        return hexString
    }

    fun createOwnColor(seekBar: SeekBar?, progress: Int): Int {
        when (seekBar?.id) {
            R.id.redSeekBar -> red = progress
            R.id.greenSeekBar -> green = progress
            R.id.blueSeekBar -> blue = progress
        }
        setColors(red, green, blue)
        return Color.argb(255,
            _colors.value!!.red,
            _colors.value!!.green,
            _colors.value!!.blue
        )
    }

    fun getColor(): Int {
        return Color.argb(
            255,
            _colors.value!!.red,
            _colors.value!!.green,
            _colors.value!!.blue
        )
    }

    private fun setColors(red: Int, green: Int, blue: Int, array: Array<String>) {
        _colors.value = Colors(red, green, blue, array)
    }

    private fun setColors(red: Int, green: Int, blue: Int) {
        _colors.value = Colors(red, green, blue)
    }



}