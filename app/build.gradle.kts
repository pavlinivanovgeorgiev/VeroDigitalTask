plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.verodigitaltask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.verodigitaltask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":feature:tasks"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    kapt("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
}
