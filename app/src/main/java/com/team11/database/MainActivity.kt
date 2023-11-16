package com.team11.database

import AppDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.searchbyfood.R


class MainActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터베이스 설정
        setDatabase()
    }

    private fun setDatabase() {

        //데이터베이스 지정
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Database-Test1"
        ).build()
    }

    public fun getDatabase(): AppDatabase {
        return database
    }
}