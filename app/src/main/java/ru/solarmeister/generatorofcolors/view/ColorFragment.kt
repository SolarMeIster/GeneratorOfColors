package ru.solarmeister.generatorofcolors.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.solarmeister.generatorofcolors.databinding.FragmentColorsBinding
import ru.solarmeister.generatorofcolors.viewmodel.ColorViewModel

class ColorFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: FragmentColorsBinding

    //варианты действий
    private val variations = arrayOf("Случайный цвет", "Свой цвет")

    private val colorViewModel: ColorViewModel by viewModels()


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
        val arrayAdapter = ArrayAdapter<Any?>(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            variations
        )

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
                        }
                        variations[1] -> {
                            alphaSeekBar.progress = colorViewModel.colors.value!!.alpha
                            redSeekBar.progress = colorViewModel.colors.value!!.red
                            greenSeekBar.progress = colorViewModel.colors.value!!.green
                            blueSeekBar.progress = colorViewModel.colors.value!!.blue
                            rgbSeekBars.visibility = View.VISIBLE
                            btnColors.visibility = View.GONE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            binding.btnColors.setOnClickListener {
                binding.fragmentColors.setBackgroundColor(colorViewModel.generatorColor())
            }

            blueSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            greenSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            redSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            alphaSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.fragmentColors.setBackgroundColor(colorViewModel.createOwnColor(seekBar, progress))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}