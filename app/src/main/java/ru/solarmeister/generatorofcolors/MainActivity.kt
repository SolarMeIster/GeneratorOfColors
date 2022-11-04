package ru.solarmeister.generatorofcolors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.solarmeister.generatorofcolors.databinding.ActivityMainBinding
import androidx.fragment.app.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ColorFragment>(R.id.fragment_container_view)
        }
    }
}