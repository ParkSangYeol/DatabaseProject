import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.Food
import com.team11.database.Data.Ingredient
import com.team11.database.MainActivity
import com.team11.database.R

class IngredientAdapter (private val ingredientDataset: Array<Ingredient>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    class IngredientViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val ingredientContent: LinearLayout = view.findViewById(R.id.ingredient_content)
        val ingredientNum: TextView = view.findViewById(R.id.ingredient_num)
        val ingredientName: TextView = view.findViewById(R.id.ingredient_name)
        val ingredientCondition: TextView = view.findViewById(R.id.ingredient_condition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val ingredientContent = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_ingredient, parent, false)

        return IngredientViewHolder(ingredientContent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.ingredientNum.text = ingredientDataset[position].Inumber.toString()
        holder.ingredientName.text = ingredientDataset[position].Iname
        holder.ingredientCondition.text = ingredientDataset[position].Icondition


        holder.ingredientContent.setOnClickListener {
            val bundle = bundleOf("Inumber" to ingredientDataset[position].Inumber,
                "Iname" to ingredientDataset[position].Iname, "Icondition" to ingredientDataset[position].Icondition)
            holder.view.findNavController().navigate(R.id.action_ingredientFragment_to_ingredientInfoFragment, bundle)
        }
    }

    override fun getItemCount() = ingredientDataset.size
}