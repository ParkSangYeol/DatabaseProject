import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Food
import com.team11.database.Data.Ingredient
import com.team11.database.MainActivity
import com.team11.database.R
import com.team11.database.View.ButtonAdapter
import com.team11.database.View.FoodInfoAdapter

class IngredientAdapter (private val ingredientDataset: Array<Ingredient>, private val context: Context) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    class IngredientViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val ingredientContent: CardView = view.findViewById(R.id.content)
        val ingredientName: TextView = view.findViewById(R.id.text_ingredient_name)
        val ingredientCondition: Button = view.findViewById(R.id.button_ingredient_state)
        val ingredientPoisons: RecyclerView  = view.findViewById(R.id.recycler_ingredient_poison)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val ingredientContent = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_food_info, parent, false)
        val holder: IngredientAdapter.IngredientViewHolder = IngredientAdapter.IngredientViewHolder(ingredientContent)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.ingredientPoisons.setLayoutManager(layoutManager)

        return holder
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.ingredientName.text = ingredientDataset[position].Iname
        holder.ingredientCondition.text = ingredientDataset[position].Icondition
        val poisonList = AppDatabase.getDatabase(context).FPDao()
            .findFpNameByInumber(ingredientDataset[position].Inumber)
        holder.ingredientPoisons.adapter = ButtonAdapter(poisonList.toTypedArray(), context)

        holder.ingredientContent.setOnClickListener {
            val bundle = bundleOf("Inumber" to ingredientDataset[position].Inumber,
                "Iname" to ingredientDataset[position].Iname, "Icondition" to ingredientDataset[position].Icondition)
            holder.view.findNavController().navigate(R.id.action_ingredientFragment_to_ingredientInfoFragment, bundle)
        }
    }

    override fun getItemCount() = ingredientDataset.size
}