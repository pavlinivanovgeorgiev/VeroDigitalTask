package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.TaskDao
import com.example.database.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class VeroDigitalDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
