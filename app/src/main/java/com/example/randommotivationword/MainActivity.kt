package com.example.randommotivationword

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.randommotivationword.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    lateinit var activity: MainActivity
    private lateinit var binding: ActivityMainBinding
    private var state: Boolean = true
//    TODO: отображение текста на кнопке, надо поменять шрифт и перенос у шрифта

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val map = Map()
        var texto = map.motivationalPhrases
        val mapo = map.motivationalPhrases
        val swearMapo = map.swearPhrases

        binding.swearmode.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Swear mod ON" else "Swear mod OFF"
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()

            if (isChecked) {
                texto = swearMapo
            } else {
                texto = mapo
            }
        }

        binding.button.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                val rand = Random.nextInt(1, 25)
                if (SystemClock.elapsedRealtime() - lastClickTime < 200) {
                    val layout = binding.button.parent as LinearLayout
                    val buttonLayoutParams = binding.button.layoutParams as LinearLayout.LayoutParams
                    val switchLayoutParams = binding.swearmode.layoutParams as LinearLayout.LayoutParams

                    if (state) {
                        buttonLayoutParams.weight = 2f
                        switchLayoutParams.weight = 0f
                    } else {
                        buttonLayoutParams.weight = 0f
                        switchLayoutParams.weight = 2f
                    }

                    binding.button.layoutParams = buttonLayoutParams
                    binding.swearmode.layoutParams = switchLayoutParams
                    state = !state
                }
                lastClickTime = SystemClock.elapsedRealtime()
                binding.button.text = texto[rand]
            }
        })

            fun isDarkThemeEnabled(context: Context): Boolean {
                return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO
            }
            if (isDarkThemeEnabled(applicationContext)) {
                    binding.button.setBackgroundColor(Color.WHITE)
                    binding.button.setTextColor(Color.BLACK)
                } else {
                    binding.button.setBackgroundColor(Color.BLACK)
                    binding.button.setTextColor(Color.WHITE)
                }
        }
    }