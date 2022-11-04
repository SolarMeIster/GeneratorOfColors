package ru.solarmeister.generatorofcolors

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.transition.Visibility
import ru.solarmeister.generatorofcolors.databinding.FragmentColorsBinding
import kotlin.random.Random

class ColorFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: FragmentColorsBinding

    //варианты действий
    private val variations = arrayOf("Случайный цвет", "Свой цвет")

    //действие SeekBar
    private var action = Action.RANDOM_COLOR

    // цвета
    private var green = 0
    private var blue = 0
    private var red = 0
    private var alpha = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter = ArrayAdapter<Any?>(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, variations)
        with(binding) {
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (parent?.getItemAtPosition(position).toString()) {
                        variations[0] -> {
                            rgbSeekBars.visibility = View.GONE
                            btnColors.visibility = View.VISIBLE
                            action = Action.RANDOM_COLOR
                        }
                        variations[1] -> {
                            alphaSeekBar.progress = alpha
                            greenSeekBar.progress = green
                            blueSeekBar.progress = blue
                            redSeekBar.progress = red
                            rgbSeekBars.visibility = View.VISIBLE
                            btnColors.visibility = View.GONE
                            action = Action.OWN_COLOR
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            when (action) {
                Action.RANDOM_COLOR -> {
                    binding.btnColors.setOnClickListener {
                        generateColor()
                    }
                }
                else -> {}
            }
            blueSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            greenSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            redSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            alphaSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
        }
    }

    private fun generateColor() {
        alpha = Random.nextInt(0,256)
        red = Random.nextInt(0,256)
        green = Random.nextInt(0,256)
        blue = Random.nextInt(0,256)
        val color = Color.argb(
            alpha,
            red,
            green,
            blue
        )
        binding.fragmentColors.setBackgroundColor(color)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar?.id) {
            R.id.greenSeekBar -> green = progress
            R.id.blueSeekBar -> blue = progress
            R.id.redSeekBar -> red = progress
            R.id.alphaSeekBar -> {
                alpha = progress
            }
        }
        binding.fragmentColors.setBackgroundColor(Color.argb(
            alpha, red, green, blue
        ))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}