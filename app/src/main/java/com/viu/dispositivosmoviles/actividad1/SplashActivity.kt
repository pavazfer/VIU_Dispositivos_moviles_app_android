package com.viu.dispositivosmoviles.actividad1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // Iniciar la siguiente actividad aquí
            val intent = Intent(this, InicioDomesticActivities::class.java)
            startActivity(intent)
            finish()
        }, 5000) // 5 segundos
    }
}
