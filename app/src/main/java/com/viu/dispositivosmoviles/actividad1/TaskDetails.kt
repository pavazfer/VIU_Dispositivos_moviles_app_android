package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class TaskDetails: AppCompatActivity() {
    var name: String = ""
    var description: String = ""
    var deadline: String = ""
    var materials: String = ""
    var family: String = ""
    var advance: Int = 0

    private lateinit var adaptador: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cambiar el color de la barra de estado
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.rojo, theme)
        }

        setContentView(R.layout.activity_task_detail)

        val taskName = intent.getStringExtra("taskName")
        val taskDescription = intent.getStringExtra("taskDescription")
        val taskDeadline = intent.getStringExtra("taskDeadline")
        val taskMaterials = intent.getStringExtra("taskMaterials")
        val taskFamily = intent.getStringExtra("taskFamily")
        val taskAdvance = intent.getIntExtra("taskAdvance", 0)

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

        val textRangeSlider = findViewById<RangeSlider>(R.id.rangeSlider)
        textRangeSlider.setValues(taskAdvance.toFloat())

        val buttonAtras = findViewById<Button>(R.id.buttonAtras)
        buttonAtras.setOnClickListener {
            finish() // Finaliza la actividad actual al hacer clic en el botón "Atrás"
        }

        adaptador = TasksAdapter(this, TasksUtils.getDefaultTasks())

        val rangeSlider = findViewById<RangeSlider>(R.id.rangeSlider)
        val buttonSaveProgress = findViewById<Button>(R.id.saveProgressButton)
        buttonSaveProgress.setOnClickListener {
            if (taskName != null) {
                modifyProgress(rangeSlider, taskName)
            }
            finish()
        }

    }

    private fun modifyProgress(rangeSlider: RangeSlider, taskName: String){
        // Lógica para modificar el progreso
        val progress = rangeSlider.values.first()

        val message = "Tarea $taskName con progreso a $progress."
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        val updatedTasks = TasksUtils.getDefaultTasks().map { task ->
            if (task.name == taskName) {
                task.advance = progress.toInt() // Actualizar el campo advance con el progreso
            }
            task
        }

        // Actualiza la lista de tareas en TasksUtils con la lista actualizada
        TasksUtils.updateTasks(updatedTasks)

        // Notifica al adaptador que se han realizado cambios en los datos
        adaptador.notifyDataSetChanged()
    }
}