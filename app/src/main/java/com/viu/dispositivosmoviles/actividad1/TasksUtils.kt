package com.viu.dispositivosmoviles.actividad1

class TasksUtils {

    companion object {

        private val defaultTasksList = arrayListOf<ShowTaskDetailsActivity>()

        fun getDefaultTasks(): List<ShowTaskDetailsActivity> {
            return defaultTasksList
        }

       init {

            // Agrega tareas predeterminadas a la lista
            val task1 = ShowTaskDetailsActivity()
            task1.name = "Cocinar"
            task1.description = "Realizar las comidas diarias"
            task1.deadline = "[sin fecha]"
            task1.materials = "Alimentos y utensilios de cocina"

            val task2 = ShowTaskDetailsActivity()
            task2.name = "Limpiar"
            task2.description = "Limpieza de la casa"
            task2.deadline = "05/11/2023"
            task2.materials = "Fregona, friegasuelos y cubo"

            val task3 = ShowTaskDetailsActivity()
            task3.name = "Fregar los platos"
            task3.description = "Depués de cada comida, fregar los platos manchados"
            task3.deadline = "[sin fecha]"
            task3.materials = "Agua, esponja y jabón"


            defaultTasksList.add(task1)
            defaultTasksList.add(task2)
            defaultTasksList.add(task3)
        }

        fun updateTasks(updatedTasks: List<ShowTaskDetailsActivity>) {
            defaultTasksList.clear()
            defaultTasksList.addAll(updatedTasks)
        }
    }
}