package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowDomesticTasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}
