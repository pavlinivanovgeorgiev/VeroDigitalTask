package com.example.tasks.data

import com.example.database.entity.TaskEntity
import com.example.network.model.TaskDto
import com.example.tasks.domain.Task
import com.google.gson.Gson


fun TaskDto.toEntity(gson: Gson): TaskEntity =
    TaskEntity(
        task = task,
        title = title,
        description = description,
        sort = sort,
        wageType = wageType,
        BusinessUnitKey = BusinessUnitKey,
        businessUnit = businessUnit,
        parentTaskID = parentTaskID,
        preplanningBoardQuickSelect = preplanningBoardQuickSelect,
        colorCode = colorCode,
        workingTime = workingTime,
        isAvailableInTimeTrackingKioskMode = isAvailableInTimeTrackingKioskMode,
        isAbstract = isAbstract,
        externalId = externalId,
        rawJson = gson.toJson(this)
    )

fun TaskEntity.toDomain(): Task =
    Task(
        task = task,
        title = title,
        description = description,
        sort = sort,
        wageType = wageType,
        BusinessUnitKey = BusinessUnitKey,
        businessUnit = businessUnit,
        parentTaskID = parentTaskID,
        preplanningBoardQuickSelect = preplanningBoardQuickSelect,
        colorCode = colorCode,
        workingTime = workingTime,
        isAvailableInTimeTrackingKioskMode = isAvailableInTimeTrackingKioskMode,
        isAbstract = isAbstract,
        externalId = externalId,
        rawJson = rawJson
    )

fun Task.matchesQuery(query: String): Boolean {
    if (query.isBlank()) return true
    val q = query.lowercase()

    return buildString {
        append(task).append(' ')
        append(title ?: "").append(' ')
        append(description ?: "").append(' ')
        append(sort ?: "").append(' ')
        append(wageType ?: "").append(' ')
        append(BusinessUnitKey ?: "").append(' ')
        append(businessUnit ?: "").append(' ')
        append(parentTaskID ?: "").append(' ')
        append(preplanningBoardQuickSelect ?: "").append(' ')
        append(colorCode ?: "").append(' ')
        append(rawJson ?: "")
    }.lowercase().contains(q)
}
