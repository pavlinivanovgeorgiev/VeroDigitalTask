package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>)

    @Query("DELETE FROM tasks")
    suspend fun clear()
}