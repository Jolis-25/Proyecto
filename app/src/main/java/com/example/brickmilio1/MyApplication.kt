package com.example.brickmilio1

import android.app.Application
import com.example.brickmilio1.db.AppDatabase

class MyApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}