package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AssignTasks: AppCompatActivity() {

    private lateinit var taskNameSpinner: Spinner
    private lateinit var familyMemberSpinner: Spinner
    private lateinit var adaptador: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cambiar el color de la barra de estado
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.verde, theme)
        }

        setContentView(R.layout.activity_assign_tasks)

        taskNameSpinner = findViewById(R.id.taskSpinner)
        familyMemberSpinner = findViewById(R.id.familyMemberSpinner)

        val buttonAtras = findViewById<Button>(R.id.backButton)
        buttonAtras.setOnClickListener {
            finish() // Finaliza la actividad actual al hacer clic en el botón "Atrás"
        }

        val buttonAssign = findViewById<Button>(R.id.assignButton)
        buttonAssign.setOnClickListener {
            val selectedTask = taskNameSpinner.selectedItem.toString()
            val selectedFamilyMember = familyMemberSpinner.selectedItem.toString()
            assignTaskToFamilyMember(selectedTask, selectedFamilyMember)
            finish()
        }

        val taskNames = TasksUtils.getDefaultTasks().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, taskNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskNameSpinner.adapter = adapter

        val familyMembers = FamilyUtils.getFamilyMembers()
        val familyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, familyMembers)
        familyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        familyMemberSpinner.adapter = familyAdapter

        adaptador = TasksAdapter(this, TasksUtils.getDefaultTasks())
    }

    private fun assignTaskToFamilyMember(taskName: String, familyMember: String) {
        // Lógica para asignar la tarea al miembro de la familia seleccionado
        val message = "Tarea $taskName asignada a $familyMember."
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // Actualiza la lista de tareas con el nombre del miembro de la familia asignado
        val updatedTasks = TasksUtils.getDefaultTasks().map { task ->
            if (task.name == taskName) {
                task.family = familyMember
            }
            task
        }

        // Actualiza la lista de tareas en TasksUtils con la lista actualizada
        TasksUtils.updateTasks(updatedTasks)

        // Notifica al adaptador que se han realizado cambios en los datos
        adaptador.notifyDataSetChanged()
    }

    private fun getImageResourceForTask(taskName: String): Int {
        return when (taskName) {
            "Cocinar" -> R.drawable.cocina
            "Limpiar" -> R.drawable.limpieza
            "Fregar los platos" -> R.drawable.lavar_platos
            else -> R.drawable.casa
        }
    }
}
