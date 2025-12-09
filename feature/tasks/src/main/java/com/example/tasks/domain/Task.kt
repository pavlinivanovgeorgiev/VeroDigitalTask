package com.example.tasks.domain

data class Task(
    val task: String,
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