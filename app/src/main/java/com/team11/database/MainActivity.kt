package com.team11.database
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.*
import com.team11.database.Data.AppDatabase
import com.team11.database.Fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터베이스 설정
        setDatabase()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, HomeFragment())
            .commit()
    }

    private fun setDatabase() {
        //데이터베이스 지정
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-test1"
        ).allowMainThreadQueries()
            .build()
    }

    public fun getDatabase(): AppDatabase {
        return database
    }
}

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


