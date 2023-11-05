package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowTasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cambiar el color de la barra de estado
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.azul, theme)
        }

        setContentView(R.layout.activity_show_tasks)

        val recyclerViewTareas = findViewById<RecyclerView>(R.id.recyclerViewTareas)

        // Configura el RecyclerView con un adaptador para mostrar la lista de tareas
        val adaptador = TasksAdapter(this, TasksUtils.getDefaultTasks())
        recyclerViewTareas.adapter = adaptador
        recyclerViewTareas.layoutManager = LinearLayoutManager(this)

        val buttonAtras = findViewById<Button>(R.id.buttonAtras)
        buttonAtras.setOnClickListener {
            finish() // Finaliza la actividad actual al hacer clic en el botón "Atrás"
        }
    }

    override fun onResume() {
        super.onResume()
        val recyclerViewTareas = findViewById<RecyclerView>(R.id.recyclerViewTareas)
        val adaptador = TasksAdapter(this, TasksUtils.getDefaultTasks())
        recyclerViewTareas.adapter = adaptador
        recyclerViewTareas.layoutManager = LinearLayoutManager(this)
    }
}
