package com.team11.database.Data

import androidx.room.AutoMigration
import androidx.room.Database
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
}