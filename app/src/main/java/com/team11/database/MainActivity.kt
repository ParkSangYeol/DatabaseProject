package com.team11.database
import android.os.Bundle
import android.widget.Toast
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
            AppDatabase::class.java, "database-test2"
        ).allowMainThreadQueries()
            .build()
    }

    public fun getDatabase(): AppDatabase {
        return database
    }
}


