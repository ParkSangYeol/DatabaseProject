package com.team11.database.Data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.team11.database.*

@Database(entities =
[Food::class,
    Consists_of::class,
    Ingredient::class,
    Triggers::class,
    Related_disease::class,
    Food_poisoning::class,
    Has::class,
    Symptom::class,
    Frequency_of_month::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun FoodDao(): FoodDao
    abstract fun IngredientDao(): IngredientDao
    abstract fun FPDao(): FPDao
    abstract fun SymptomDao(): SymptomDao
    abstract fun FoMDao(): FoMDao
    abstract fun RDDao(): RDDao
    abstract fun DevDao(): DevDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database-test2"
                ).allowMainThreadQueries()
                    .createFromAsset("myDatabase.db")
                    .build()
                INSTANCE = instance
                // 반환
                instance
            }
        }
    }
}