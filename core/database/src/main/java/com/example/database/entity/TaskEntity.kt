package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val task: String,
    val title: String?,
    val description: String?,
    val sort: String?,
    val wageType: String?,
    val BusinessUnitKey: String?,
    val businessUnit: String?,
    val parentTaskID: String?,
    val preplanningBoardQuickSelect: String?,
    val colorCode: String?,
    val workingTime: String?,
    val isAvailableInTimeTrackingKioskMode: Boolean?,
    val isAbstract: Boolean?,
    val externalId: String?,
    val rawJson: String?
)