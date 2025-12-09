Android Task – Technical Summary

This project implements the required assignment using a clean, modular, and production-oriented Android architecture. 
The application retrieves task data from a remote API, stores it locally for offline access, provides search capabilities across all task fields, and supports QR-based task lookup.

Architecture
The codebase is organized into feature-based and core modules:

:app  
:core:network  
:core:database  
:core:common  
:feature:tasks


Each module encapsulates a specific responsibility:

core:network – Retrofit configuration, API interfaces, DTOs

core:database – Room entities, DAO, database provider

feature:tasks – domain model, repository, ViewModel, UI layer, worker, QR scanner

app – application entry point and navigation host

This structure ensures separation of concerns, scalability, and clean dependency flow.

Key Functionality

Authenticated API communication (login + task retrieval via Bearer token)
Local caching using Room for offline usability
Full-field search across all task properties
QR code scanning (ZXing), used to automatically populate the search query
Swipe-to-refresh UI interaction

Periodic background synchronization via WorkManager (every 60 minutes)
Jetpack Compose UI, Material 3 design
Dependency Injection with Hilt for core dependencies and ViewModels


Technologies Used

Kotlin

Jetpack Compose

Material 3

Retrofit + OkHttp

Gson

Room Database

WorkManager

ZXing QR Scanner

Dagger Hilt

Modular Android Architecture

Summary

The project demonstrates a clean, maintainable, and scalable Android implementation aligned with modern best practices: modular architecture, declarative UI, offline support, background synchronization, and robust dependency management.
