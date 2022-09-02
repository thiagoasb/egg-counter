package com.thiagoaraujo.eggcounter

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.thiagoaraujo.eggcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sprefs: SharedPreferences
    private var secretNumber = 0
    private var count = 0
    private var state = 0
    private var resetState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sprefs = getSharedPreferences("sp_counter", Context.MODE_PRIVATE)
        count = sprefs.getInt("count", 0)
        secretNumber = sprefs.getInt("secret", getSecretNumber())

        binding.tvEggCounter.text = count.toString()

        binding.ivEgg.setOnClickListener {
            when (state) {
                0 -> addAnEgg(secretNumber)
                1 -> {
                    if (resetState == 3) {
                        resetState()
                    }
                    resetState++
                }
            }
        }

        binding.ivEgg.setOnLongClickListener {
            resetState()
            Toast.makeText(this, "Counter's egg restarted", Toast.LENGTH_SHORT).show()
            true
        }

    }

    private fun addAnEgg(secretNumber: Int) {
        count++
        sprefs.edit().apply {
            putInt("count", count)
            putInt("secret", secretNumber)
            apply()
        }
        if (count >= secretNumber) {
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
        resetState = 0
        sprefs.edit().clear().apply()
        secretNumber = getSecretNumber()
        binding.ivEgg.setImageResource(R.drawable.one_egg)
        binding.tvEggCounter.text = count.toString()
        return
    }

    private fun getSecretNumber(): Int {
        return (1..30).random()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_manu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.show_counter -> createEggCounterDialog()
            R.id.show_info -> createInfoDialog()
        }
        return true
    }

    private fun createEggCounterDialog() {
        val seeRandomNumber = AlertDialog.Builder(this)
            .setTitle(":-)")
            .setMessage(getString(R.string.count_eggs_message, secretNumber.toString()))
            .setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.create()
        seeRandomNumber.show()
    }

    private fun createInfoDialog() {
        val seeRandomNumber = AlertDialog.Builder(this)
            .setTitle("Info")
            .setMessage("Click until the secret number is reached, hold the screen to reset the counter and if want to see the secret number, press the eye icon")
            .setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.create()
        seeRandomNumber.show()
    }
}