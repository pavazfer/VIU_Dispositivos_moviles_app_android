package com.viu.dispositivosmoviles.actividad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InicioDomesticActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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