plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    implementation(libs.androidx.core.core.ktx)

    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core)

    // Dagger Hilt
    implementation(libs.com.google.dagger.hilt.android)
    androidTestImplementation(libs.com.google.dagger.hilt.android.testing)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    kaptAndroidTest(libs.com.google.dagger.hilt.android.compiler)

    // Media
    implementation(libs.androidx.media)

    // Exo Player
    implementation(libs.com.google.android.exoplayer.exoplayer.core)

    // Glide
    implementation(libs.com.github.bumptech.glide)
    kapt(libs.com.github.bumptech.glide.compiler)

    // Utils
    implementation(libs.com.jakewharton.timber)

    // Instrumentation tests
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Local unit tests
    testImplementation(libs.androidx.test.ext.junit)
}