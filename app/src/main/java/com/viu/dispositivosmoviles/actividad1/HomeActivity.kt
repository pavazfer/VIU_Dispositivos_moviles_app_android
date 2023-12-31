package com.viu.dispositivosmoviles.actividad1

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

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
            val intent = Intent(this, ShowTasksActivity::class.java)
            startActivity(intent)
        }

        buttonAgregarTarea.setOnClickListener {
            // Implementa la lógica para agregar una nueva tarea doméstica
            val intent = Intent(this, CreateTasksActivity::class.java)
            startActivity(intent)
        }

        buttonAsignarTareas.setOnClickListener {
            // Implementa la lógica para asignar tareas a otros miembros de la familia
            val intent = Intent(this, AssignTasksActivity::class.java)
            startActivity(intent)
        }
    }
}