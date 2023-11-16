import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "FOOD")
data class Food (
    @PrimaryKey val Fnumber: Int,
    @ColumnInfo(name = "Fname") var Fname: String?
)

@Entity(tableName = "CONSISTS_OF", primaryKeys = ["Fnum", "Inum"])
data class Consists_of (
    val Fnum: Int,
    val Inum: Int
)

@Entity(tableName = "INGREDIENT")
data class Ingredient (
    @PrimaryKey val Inumber: Int,
    @ColumnInfo(name = "Iname") val Iname: String?,
    @ColumnInfo(name = "Icondition") val Icondition: String?
)

@Entity(tableName = "TRIGGERS", primaryKeys = ["Inum", "FPnum"])
data class Triggers (
    val Inum: Int,
    val FPnum: Int
)

@Entity(tableName = "FOOD_POISONING")
data class Food_poisoning (
    @PrimaryKey val FPnumber: Int,
    @ColumnInfo(name = "CAname") val CAname: String?,
    @ColumnInfo(name = "Temperature") val Temperature: Int,
    @ColumnInfo(name = "Time") val Time: Int,
    @ColumnInfo(name = "Min_IP") val Min_IP: Int,
    @ColumnInfo(name = "Max_IP") val Max_IP: Int
)

@Dao
interface FoodDao
{
    @Insert
    fun insertFood(food: Food)

    @Query("SELECT * FROM FOOD")
    fun getAll(): List<Food>
}

@Dao
interface IngredientDao{
    @Query("SELECT * FROM INGREDIENT, FOOD, CONSISTS_OF WHERE Inumber = Inum AND Inum = Fnum AND Fnumber = Fnum AND Fname = :name")
    fun findIngredientByFname(name: String?): List<Ingredient>
}

@Database(entities = [Food::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun FoodDao(): FoodDao
}