package ru.solarmeister.generatorofcolors.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import ru.solarmeister.generatorofcolors.databinding.FragmentColorsBinding
import ru.solarmeister.generatorofcolors.viewmodel.ColorViewModel

class ColorFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: FragmentColorsBinding

    //варианты действи
    private var variationsCl: Array<String> = arrayOf("Случайный цвет", "Свой цвет")

    private val colorViewModel: ColorViewModel by activityViewModels()

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
        binding.btnColors.setBackgroundColor(Color.parseColor("#393939"))
        val arrayAdapter = ArrayAdapter<Any?>(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            colorViewModel.colors.value!!.variations
        )

        with(binding) {
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (parent.getItemAtPosition(position).toString()) {
                        variationsCl[0] -> {
                            rgbSeekBars.visibility = View.GONE
                            btnColors.visibility = View.VISIBLE
                        }
                        variationsCl[1] -> {
                            if (colorViewModel.colors.value != null) {
                                redSeekBar.progress = colorViewModel.colors.value!!.red
                                greenSeekBar.progress = colorViewModel.colors.value!!.green
                                blueSeekBar.progress = colorViewModel.colors.value!!.blue
                            }
                            rgbSeekBars.visibility = View.VISIBLE
                            btnColors.visibility = View.GONE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            binding.btnColors.setOnClickListener {
                val color = colorViewModel.generatorColor()
                val window = activity?.window
                binding.fragmentColors.setBackgroundColor(color)
                window?.statusBarColor = color
                window?.navigationBarColor = color
                binding.hexText.text = colorViewModel.convertToHex()
            }

            blueSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            greenSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
            redSeekBar.setOnSeekBarChangeListener(this@ColorFragment)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val color = colorViewModel.createOwnColor(seekBar, progress)
        val window = activity?.window
        binding.fragmentColors.setBackgroundColor(color)
        window?.navigationBarColor = color
        window?.statusBarColor = color
        binding.hexText.text = colorViewModel.convertToHex(
            colorViewModel.colors.value!!.red,
            colorViewModel.colors.value!!.green,
            colorViewModel.colors.value!!.blue
        )
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}


}