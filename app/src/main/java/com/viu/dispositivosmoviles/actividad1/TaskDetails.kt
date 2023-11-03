package com.viu.dispositivosmoviles.actividad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class TaskDetails: AppCompatActivity() {
    var name: String = ""
    var description: String = ""
    var deadline: String = ""
    var materials: String = ""
    var family: String = ""
    var advance: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val taskName = intent.getStringExtra("taskName")
        val taskDescription = intent.getStringExtra("taskDescription")
        val taskDeadline = intent.getStringExtra("taskDeadline")
        val taskMaterials = intent.getStringExtra("taskMaterials")
        val taskFamily = intent.getStringExtra("taskFamily")

        // Usa los datos recuperados para actualizar las vistas correspondientes en tu layout de detalle de tarea
        val textViewName = findViewById<TextView>(R.id.textViewName)
        textViewName.text = taskName

        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        textViewDescription.text = taskDescription

        val textViewDeadline = findViewById<TextView>(R.id.textViewDeadline)
        textViewDeadline.text = taskDeadline

        val textViewMaterials = findViewById<TextView>(R.id.textViewMaterials)
        textViewMaterials.text = taskMaterials

        val textViewFamily = findViewById<TextView>(R.id.textViewFamily)
        textViewFamily.text = taskFamily

        val buttonAtras = findViewById<Button>(R.id.buttonAtras)
        buttonAtras.setOnClickListener {
            finish() // Finaliza la actividad actual al hacer clic en el botón "Atrás"
        }

        val rangeSlider = findViewById<RangeSlider>(R.id.rangeSlider)
        val buttonSaveProgress = findViewById<Button>(R.id.saveProgressButton)
        buttonSaveProgress.setOnClickListener {
            val progress = rangeSlider.values.first()
            val intent = Intent(this, ShowDomesticTasks::class.java)
            intent.putExtra("progress", progress)
            startActivity(intent)
            finish()
        }
    }
}