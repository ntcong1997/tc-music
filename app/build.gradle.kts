plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        applicationId = "com.example.tcmusic"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false

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

    buildFeatures {
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":player"))
    testImplementation(project(":test-data"))

    // Core
    implementation(libs.androidx.core.core.ktx)
    implementation(libs.androidx.core.core.splashscreen)
    testImplementation(libs.androidx.arch.core.core.testing)

    // UI
    implementation(libs.androidx.activity.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.com.google.accompanist.accompanist.pager)
    implementation(libs.com.google.accompanist.accompanist.pager.indicators)

    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)

    // Architecture Components
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.lifecycle.runtime.ktx)
    kapt(libs.androidx.lifecycle.lifecycle.compiler)

    // Compose
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.ui.tooling)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)

    // Navigation
    implementation(libs.androidx.navigation.navigation.compose)

    // Media
    implementation(libs.androidx.media)

    // Dagger Hilt
    implementation(libs.com.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.hilt.navigation.compose)
    androidTestImplementation(libs.com.google.dagger.hilt.android.testing)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    kaptAndroidTest(libs.com.google.dagger.hilt.android.compiler)

    // Coroutines
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Coil
    implementation(libs.io.coil.kt.coil.base)
    implementation(libs.io.coil.kt.coil.compose)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)
    implementation(libs.androidx.paging.paging.compose)

    // Retrofit & Okhttp
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)

    // Utils
    implementation(libs.id.zelory.compressor)
    implementation(libs.com.jakewharton.timber)
    implementation(libs.com.google.code.gson)

    // Instrumentation tests
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Local unit tests
    testImplementation(libs.junit)
    testImplementation(libs.org.mockito.kotlin)
}