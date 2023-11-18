package com.team11.database.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao
{
    @Query("SELECT * FROM FOOD")
    fun getAll(): List<Food>
}

@Dao
interface IngredientDao{
    @Insert
    fun insertIngredient(ingredient: Ingredient)
    @Query("SELECT * FROM INGREDIENT")
    fun getAll(): List<Ingredient>
    @Query("SELECT * FROM INGREDIENT, FOOD, CONSISTS_OF WHERE Inumber = Inum AND Inum = Fnum AND Fnumber = Fnum AND Fname = :name")
    fun findIngredientByFname(name: String?): List<Ingredient>
}

@Dao
interface FPDao
{
    @Query("SELECT * FROM FOOD_POISONING")
    fun getAll(): List<Food_poisoning>
}

@Dao
interface SymptomDao
{
    @Query("SELECT * FROM SYMPTOM")
    fun getAll(): List<Symptom>
}

@Dao
interface FoMDao
{
    @Query("SELECT * FROM FREQUENCY_OF_MONTH")
    fun getAll(): List<Frequency_of_month>
}

@Dao
interface RDDao
{
    @Query("SELECT * FROM RELATED_DISEASE")
    fun getAll(): List<Related_disease>
}