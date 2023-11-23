package com.team11.database.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "FOOD")
data class Food (
    @ColumnInfo(name = "Fnumber") @PrimaryKey val Fnumber: Int,
    @ColumnInfo(name = "Fname") var Fname: String
)

@Entity(tableName = "CONSISTS_OF", primaryKeys = ["Fnum", "Inum"],
    foreignKeys = [ForeignKey(entity = Food::class, parentColumns = ["Fnumber"], childColumns = ["Fnum"]),
        ForeignKey(entity = Ingredient::class, parentColumns = ["Inumber"], childColumns = ["Inum"])]
)
data class Consists_of (
    @ColumnInfo(name = "Fnum") val Fnum: Int,
    @ColumnInfo(name = "Inum") val Inum: Int
)

@Entity(tableName = "INGREDIENT")
data class Ingredient (
    @ColumnInfo(name = "Inumber") @PrimaryKey val Inumber: Int,
    @ColumnInfo(name = "Iname") val Iname: String,
    @ColumnInfo(name = "Icondition") val Icondition: String?
)

@Entity(tableName = "TRIGGERS", primaryKeys = ["Inum", "FPnum"],
    foreignKeys = [ForeignKey(entity = Ingredient::class, parentColumns = ["Inumber"], childColumns = ["Inum"]),
        ForeignKey(entity = Food_poisoning::class, parentColumns = ["FPnumber"], childColumns = ["FPnum"])])
data class Triggers (
    @ColumnInfo(name = "Inum") val Inum: Int,
    @ColumnInfo(name = "FPnum") val FPnum: Int
)

@Entity(tableName = "RELATED_DISEASE", primaryKeys = ["FPnum", "Dname"],
    foreignKeys = [ForeignKey(entity = Food_poisoning::class, parentColumns = ["FPnumber"], childColumns = ["FPnum"])])
data class Related_disease (
    @ColumnInfo(name = "FPnum") val FPnum: Int,
    @ColumnInfo(name = "Dname") val Dname: String
)

@Entity(tableName = "FOOD_POISONING")
data class Food_poisoning (
    @ColumnInfo(name = "FPnumber") @PrimaryKey val FPnumber: Int,
    @ColumnInfo(name = "CAname") val CAname: String,
    @ColumnInfo(name = "Temperature") val Temperature: Int,
    @ColumnInfo(name = "Time") val Time: Int,
    @ColumnInfo(name = "Min_IP") val Min_IP: Int,
    @ColumnInfo(name = "Max_IP") val Max_IP: Int
)

@Entity(tableName = "HAS", primaryKeys = ["FPnum", "Snum"],
    foreignKeys = [ForeignKey(entity = Food_poisoning::class, parentColumns = ["FPnumber"], childColumns = ["FPnum"]),
        ForeignKey(entity = Symptom::class, parentColumns = ["Snumber"], childColumns = ["Snum"])])
data class Has (
    @ColumnInfo(name = "FPnum") val FPnum: Int,
    @ColumnInfo(name = "Snum") val Snum: Int
)

@Entity(tableName = "SYMPTOM")
data class Symptom (
    @ColumnInfo(name = "Snumber") @PrimaryKey val Snumber: Int,
    @ColumnInfo(name = "Sname") val Sname: String
)

@Entity(tableName = "FREQUENCY_OF_MONTH", primaryKeys = ["FPnum", "Month"],
    foreignKeys = [ForeignKey(entity = Food_poisoning::class, parentColumns = ["FPnumber"], childColumns = ["FPnum"])])
data class Frequency_of_month (
    @ColumnInfo(name = "FPnum") val FPnum: Int,
    @ColumnInfo(name = "Month") val Month: Int,
    @ColumnInfo(name = "Frequency") val Frequency: Int
)

data class FoodPoisoningInfo(
    val MaxTemperature: Int,
    val MaxTime: Int,
    val Min_IP: Int,
    val Max_IP: Int
)