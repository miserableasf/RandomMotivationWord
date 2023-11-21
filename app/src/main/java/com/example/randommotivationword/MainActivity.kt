package com.example.randommotivationword

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.randommotivationword.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    lateinit var activity: MainActivity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var lastClickTime: Long = 0
        var state: Boolean = true

        binding.button.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                val map = Map()
                val mapo = map.motivationalPhrases

                var rand = Random.nextInt(1, mapo.size)

                var texto = mapo[rand]
                binding.button.text = texto

                if (SystemClock.elapsedRealtime() - lastClickTime < 200) {
                    binding.button.parent as LinearLayout
                    val buttonLayoutParams = binding.button.layoutParams as LinearLayout.LayoutParams
                    val switchLayoutParams = binding.darkMode.layoutParams as LinearLayout.LayoutParams

                    if (state) {
                        buttonLayoutParams.weight = 2f
                        switchLayoutParams.weight = 0f
                    } else {
                        buttonLayoutParams.weight = 0f
                        switchLayoutParams.weight = 2f
                    }

                    binding.button.layoutParams = buttonLayoutParams
                    binding.darkMode.layoutParams = switchLayoutParams
                    state = !state
                }
                lastClickTime = SystemClock.elapsedRealtime()
//                TODO: dark theme, and make on swipe this menu.
            }
        })

        binding.darkMode.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Dark mod ON" else "Dark mod OFF"
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()

            if (isChecked) {
                binding.button.setBackgroundColor(Color.BLACK)
                binding.button.setTextColor(Color.WHITE)
            } else {
                binding.button.setBackgroundColor(Color.WHITE)
                binding.button.setTextColor(Color.BLACK)
            }
        }
    }
//    TODO: сделать темный режим под стать настройкам, и так-же сделать стилизацию что-бы свитч дарк-мода не прыгал постоянно от текста к тексту.
//    FIXME: исправить баг связанный с переключением назад в светлую тему(хотя хуй его что с ним не так) П.С: при большом тексте нельзя нормально переключить режим(через раз работает аааа)
}
