package com.thiagoaraujo.eggcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private var secretNumber = 0
    private var count = 0
    private var state = 0
    private var resetState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.iv_egg)
        textView = findViewById(R.id.tv_egg_counter)
        secretNumber = getSecretNumber()

        imageView.setOnClickListener {
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

        imageView.setOnLongClickListener {
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
            imageView.setImageResource(R.drawable.many_eggs)
            state = 1
            textView.text = "$count"
            return
        }

        textView.text = "$count"
    }

    private fun resetState() {
        count = 0
        state = 0
        secretNumber = getSecretNumber()
        imageView.setImageResource(R.drawable.one_egg)
        textView.text = "$count"
        return
    }

    private fun getSecretNumber(): Int {
        return (1..30).random()
    }
}