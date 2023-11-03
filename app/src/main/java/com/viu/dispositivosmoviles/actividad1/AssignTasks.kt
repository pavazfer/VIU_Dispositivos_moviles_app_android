package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
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
        setContentView(R.layout.activity_assign_tasks)

        taskNameSpinner = findViewById(R.id.taskSpinner)
        familyMemberSpinner = findViewById(R.id.familyMemberSpinner)

        val buttonAtras = findViewById<Button>(R.id.assignButton)
        buttonAtras.setOnClickListener {
            val selectedTask = taskNameSpinner.selectedItem.toString()
            val selectedFamilyMember = familyMemberSpinner.selectedItem.toString()
            assignTaskToFamilyMember(selectedTask, selectedFamilyMember)
            finish()
        }

        val taskNames = TasksUtils.getDefaultTasks().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, taskNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskNameSpinner.adapter = adapter

        val familyMembers = arrayOf("Familiar 1", "Familiar 2", "Familiar 3") // Reemplaza con los nombres reales
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
}
