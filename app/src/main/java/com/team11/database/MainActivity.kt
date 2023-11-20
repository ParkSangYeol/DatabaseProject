package com.team11.database
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.*
import com.team11.database.Data.AppDatabase
import com.team11.database.Data.Food
import com.team11.database.Fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터베이스 설정
        setDatabase()
        //loadData()

    }

    private fun setDatabase() {
        //데이터베이스 지정
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-test2"
        ).allowMainThreadQueries()
            .createFromAsset("myDatabase.db")
            .build()
    }

    private fun loadData() {
        // 최초 데이터베이스 실행시에만 동작해야함.
        // TODO sharedprefernce 또는 별도의 방법을 이용하여 구현 필요.
        database.FoodDao().insertFood(Food(1, "햄버거"))
        database.FoodDao().insertFood(Food(2, "피자"))
        database.FoodDao().insertFood(Food(3, "하와이안 피자"))
        database.FoodDao().insertFood(Food(4, "닥터 페퍼"))
    }

    public fun getDatabase(): AppDatabase {
        return database
    }
}


