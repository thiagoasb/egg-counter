package com.thiagoaraujo.eggcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thiagoaraujo.eggcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var secretNumber = 0
    private var count = 0
    private var state = 0
    private var resetState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        secretNumber = getSecretNumber()

        binding.ivEgg.setOnClickListener {
            when (state) {
                0 -> addAnEgg(secretNumber)
                1 -> {
                    if (resetState == 3) {
                        resetState = 0
                        resetState()
                    }
                    resetState++
                }
            }
        }

        binding.ivEgg.setOnLongClickListener {
            Toast.makeText(
                this,
                getString(R.string.count_eggs_message, secretNumber.toString()),
                Toast.LENGTH_LONG
            ).show()
            true
        }

    }

    private fun addAnEgg(secretNumber: Int) {
        count++
        if (count == secretNumber) {
            binding.ivEgg.setImageResource(R.drawable.many_eggs)
            state = 1
            binding.tvEggCounter.text = count.toString()
            return
        }

        binding.tvEggCounter.text = count.toString()
    }

    private fun resetState() {
        count = 0
        state = 0
        secretNumber = getSecretNumber()
        binding.ivEgg.setImageResource(R.drawable.one_egg)
        binding.tvEggCounter.text = count.toString()
        return
    }

    private fun getSecretNumber(): Int {
        return (1..30).random()
    }
}