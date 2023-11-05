package com.viu.dispositivosmoviles.actividad1

import TasksAdapter
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class CreateTasksActivity : AppCompatActivity() {

    private lateinit var editTextTaskName: EditText
    private lateinit var editTextTaskDescription: EditText
    private lateinit var editTextMaterials: EditText
    private lateinit var spinnerFamilyMember: Spinner
    private lateinit var buttonSelectDate: Button
    private lateinit var textViewSelectedDate: TextView
    private lateinit var buttonCreateTask: Button

    private lateinit var adaptador: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cambiar el color de la barra de estado
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.amarillo, theme)
        }

        setContentView(R.layout.activity_create_task)

        editTextTaskName = findViewById(R.id.editTextTaskName)
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription)
        editTextMaterials = findViewById(R.id.editTextMaterials)
        spinnerFamilyMember = findViewById(R.id.spinnerFamilyMember)
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        buttonCreateTask = findViewById(R.id.buttonCreateTask)

        val familyMembers = FamilyUtils.getFamilyMembers()
        val familyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, familyMembers)
        familyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFamilyMember.adapter = familyAdapter

        buttonSelectDate.setOnClickListener {
            // Lógica para seleccionar la fecha
            showDatePicker()
        }

        val buttonAtras = findViewById<Button>(R.id.backButton)
        buttonAtras.setOnClickListener {
            finish() // Finaliza la actividad actual al hacer clic en el botón "Atrás"
        }

        buttonCreateTask.setOnClickListener {
            val taskName = editTextTaskName.text.toString()
            val taskDescription = editTextTaskDescription.text.toString()
            val materials = editTextMaterials.text.toString()
            val selectedDate = textViewSelectedDate.text.toString()
            val selectedFamilyMember = spinnerFamilyMember.selectedItem.toString()


            if (taskName.isNotEmpty() && taskDescription.isNotEmpty()) {
                // Lógica para crear la nueva tarea con todos los datos
                val newTask = ShowTaskDetailsActivity()
                newTask.name = taskName
                newTask.description = taskDescription
                newTask.materials = materials
                newTask.deadline = selectedDate
                newTask.family = selectedFamilyMember

                val currentTasks = TasksUtils.getDefaultTasks().toMutableList()
                currentTasks.add(newTask)
                TasksUtils.updateTasks(currentTasks)

                val message = "Nueva tarea creada: $taskName, $taskDescription"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                finish() // Finaliza la actividad después de crear la tarea
            } else {
                Toast.makeText(this, "Completa os campos para crear la tarea", Toast.LENGTH_SHORT).show()
            }
        }

        adaptador = TasksAdapter(this, TasksUtils.getDefaultTasks())
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                val selectedDate = "$day/${month + 1}/$year"
                textViewSelectedDate.text = "$selectedDate"
                Toast.makeText(this, "Fecha seleccionada $selectedDate", Toast.LENGTH_SHORT).show()
            }, year, month, day
        )
        datePickerDialog.show()
    }
}