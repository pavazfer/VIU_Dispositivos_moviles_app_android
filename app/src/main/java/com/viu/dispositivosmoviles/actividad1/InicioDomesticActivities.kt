package com.viu.dispositivosmoviles.actividad1

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InicioDomesticActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cambiar el color de la barra de estado
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.naranja, theme)
        }

        setContentView(R.layout.activity_home)

        val buttonVerTareas = findViewById<Button>(R.id.buttonVerTareas)
        val buttonAgregarTarea = findViewById<Button>(R.id.buttonAgregarTarea)
        val buttonAsignarTareas = findViewById<Button>(R.id.buttonAsignarTareas)

        buttonVerTareas.setOnClickListener {
            // Implementa la lógica para mostrar la lista de tareas diarias
            val intent = Intent(this, ShowDomesticTasks::class.java)
            startActivity(intent)
        }

        buttonAgregarTarea.setOnClickListener {
            // Implementa la lógica para agregar una nueva tarea doméstica
        }

        buttonAsignarTareas.setOnClickListener {
            // Implementa la lógica para asignar tareas a otros miembros de la familia
            val intent = Intent(this, AssignTasks::class.java)
            startActivity(intent)
        }
    }
}