package com.team11.database.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.team11.database.Food
import com.team11.database.FoodDao

@Database(entities = [Food::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun FoodDao(): FoodDao
}