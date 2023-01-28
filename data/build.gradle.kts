import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val shazamProperties = Properties().apply {
    try {
        load(FileInputStream(File(rootProject.rootDir, "shazam.properties")))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "X_RapidAPI_Key", shazamProperties.getProperty("spr.key") ?: System.getenv("SHAZAM_KEY"))
            buildConfigField("String", "X_RapidAPI_Host", "\"shazam-core.p.rapidapi.com\"")
        }

        getByName("release") {
            buildConfigField("String", "X_RapidAPI_Key", shazamProperties.getProperty("spr.key") ?: System.getenv("SHAZAM_KEY"))
            buildConfigField("String", "X_RapidAPI_Host", "\"shazam-core.p.rapidapi.com\"")

            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))
    testImplementation(project(":test-data"))

    implementation(libs.androidx.core.core.ktx)

    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)

    // Dagger Hilt
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.android.compiler)

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core)
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // DataStore
    implementation(libs.androidx.datastore.datastore.core)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Retrofit & Okhttp
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.retrofit2.converter.gson)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)

    // Room database
    implementation(libs.androidx.room.room.runtime)
    implementation(libs.androidx.room.room.ktx)
    implementation(libs.androidx.room.room.paging)
    kapt(libs.androidx.room.room.compiler)

    // Utils
    implementation(libs.com.jakewharton.timber)

    // Local unit tests
    testImplementation(libs.junit)
}