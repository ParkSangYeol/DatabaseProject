package com.team11.database.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao
{
    @Query("SELECT * FROM FOOD")
    fun getAll(): List<Food>

    @Query("SELECT * FROM FOOD WHERE FOOD.Fname = :name")
    fun findFoodByName(name: String): List<Food>

    @Insert
    fun insertFood(food: Food)
}

@Dao
interface IngredientDao{
    @Insert
    fun insertIngredient(ingredient: Ingredient)
    @Query("SELECT * FROM INGREDIENT")
    fun getAll(): List<Ingredient>

    @Query("SELECT * FROM INGREDIENT WHERE INGREDIENT.Iname = :name")
    fun findIngredientByName(name: String): List<Ingredient>

    @Query("SELECT I.Inumber, I.Iname, I.Icondition FROM INGREDIENT AS I, TRIGGERS AS T, FOOD_POISONING AS FP " +
            "WHERE I.Inumber = T.Inum AND T.FPnum = FP.FPnumber AND I.Iname = :name")
    fun findIngredientTriggersFpByName(name: String): List<Ingredient>

    @Query("SELECT I.Inumber, I.Iname, I.Icondition FROM INGREDIENT AS I, TRIGGERS AS T, FOOD_POISONING AS FP WHERE I.Inumber = T.Inum AND T.FPnum = FP.FPnumber")
    fun findIngredientTriggersFp(): List<Ingredient>

    @Query("SELECT I.Inumber, I.Iname, I.Icondition FROM INGREDIENT AS I, FOOD AS F, CONSISTS_OF AS C " +
            "WHERE I.Inumber = C.Inum AND F.Fnumber = C.Fnum AND F.Fname = :Fname")
    fun findIngredientByFname(Fname: String): List<Ingredient>

    @Query("SELECT I.Iname FROM INGREDIENT AS I, FOOD AS F, CONSISTS_OF AS C " +
            "WHERE F.Fnumber = :Fnum AND I.Inumber = C.Inum AND F.Fnumber = C.Fnum")
    fun findIngredientNameByFnumber(Fnum: Int): List<String>
}

@Dao
interface FPDao
{
    @Query("SELECT * FROM FOOD_POISONING AS FP WHERE FP.CAname = :CAname")
    fun findFpByCAname(CAname: String): List<Food_poisoning>

    @Query("SELECT FP.FPnumber, FP.CAname, FP.Temperature, FP.Time, FP.Min_IP, FP.Max_IP FROM FOOD_POISONING AS FP, TRIGGERS AS T, INGREDIENT AS I " +
            "WHERE FP.FPnumber = T.FPnum AND I.Inumber = T.Inum AND I.Iname = :Iname")
    fun findFpByIname(Iname: String?): List<Food_poisoning>

    @Query("SELECT * FROM FOOD_POISONING")
    fun getAll(): List<Food_poisoning>

    @Query("SELECT FP.CAname FROM FOOD_POISONING AS FP, TRIGGERS AS T, INGREDIENT AS I " +
            "WHERE FP.FPnumber = T.FPnum AND I.Inumber = T.Inum AND I.Iname = :Iname")
    fun findFpNameByIname(Iname: String?): List<String>

    @Query("""
        SELECT MAX(FP.Temperature) AS MaxTemperature, MAX(FP.Time) AS MaxTime, MIN(FP.Min_IP) AS Min_IP, MAX(FP.Max_IP) AS Max_IP
        FROM FOOD AS F, CONSISTS_OF AS CO, TRIGGERS AS T, FOOD_POISONING AS FP
        WHERE  F.Fnumber = CO.Fnum AND CO.Inum = T.Inum And T.FPnum = FP.FPnumber AND F.Fname = :Fname
    """)
    fun getPoisonInfoByFname(Fname: String): FoodPoisoningInfo
}

@Dao
interface SymptomDao
{
    @Query("SELECT S.Snumber, S.Sname FROM SYMPTOM AS S, FOOD_POISONING AS FP, HAS AS H " +
            "WHERE FP.FPnumber = H.FPnum AND S.Snumber = H.Snum AND FP.FPnumber = :FPnum")
    fun findSymptomByFPnum(FPnum: Int): List<Symptom>

    @Query("SELECT * FROM SYMPTOM")
    fun getAll(): List<Symptom>
}

@Dao
interface FoMDao
{
    @Query("SELECT F.FPnum, F.Month, F.Frequency FROM FREQUENCY_OF_MONTH AS F, FOOD_POISONING AS FP WHERE F.FPnum = FP.FPnumber AND FPnumber = :FPnum")
    fun findRdByFPnum(FPnum: Int): List<Frequency_of_month>
    @Query("SELECT * FROM FREQUENCY_OF_MONTH")
    fun getAll(): List<Frequency_of_month>
}

@Dao
interface RDDao
{
    @Query("SELECT R.FPnum, R.Dname FROM RELATED_DISEASE AS R, FOOD_POISONING AS FP WHERE R.FPnum = FP.FPnumber AND FP.FPnumber = :FPnum")
    fun findRdByFPnum(FPnum: Int): List<Related_disease>

    @Query("SELECT * FROM RELATED_DISEASE")
    fun getAll(): List<Related_disease>
}