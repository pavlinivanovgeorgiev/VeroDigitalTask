plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.verodigitaltask.feature.tasks"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
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
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.work.runtime.ktx)
    kapt("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // Swipe-to-refresh за Compose (ако ще ползваш)
    implementation("com.google.accompanist:accompanist-swiperefresh:0.36.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("androidx.compose.material:material-icons-extended")
}
