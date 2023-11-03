import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viu.dispositivosmoviles.actividad1.R
import com.viu.dispositivosmoviles.actividad1.TaskDetails

class TasksAdapter(private val context: Context, private val listaTareas: List<TaskDetails>) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaTareas[position]
        holder.textViewTarea.text = item.name

        // Aquí, setea la imagen correspondiente a la tarea
        val imageResId = getImageResourceForTask(item.name)
        holder.taskImageView.setImageResource(imageResId)

        // Setea el progreso en el TextView correspondiente
        holder.textViewProgress.text = "${item.advance}%"


        holder.itemView.setOnClickListener {
            val intent = Intent(context, TaskDetails::class.java)
            intent.putExtra("taskName", item.name)
            intent.putExtra("taskDescription", item.description)
            intent.putExtra("taskDeadline", item.deadline)
            intent.putExtra("taskMaterials", item.materials)
            intent.putExtra("taskFamily", item.family)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTarea: TextView = view.findViewById(R.id.textViewTarea)
        val taskImageView: ImageView = view.findViewById(R.id.taskImageView)
        val textViewProgress: TextView = view.findViewById(R.id.textViewProgress)
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