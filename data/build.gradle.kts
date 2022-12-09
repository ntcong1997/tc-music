plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
        getByName("debug") {
            buildConfigField("String", "X_RapidAPI_Key", "\"cb0c3be532msh5393ffc0637e0ecp1c6024jsn0e148e1c8e2b\"")
            buildConfigField("String", "X_RapidAPI_Host", "\"shazam-core.p.rapidapi.com\"")
        }

        getByName("release") {
            buildConfigField("String", "X_RapidAPI_Key", "\"cb0c3be532msh5393ffc0637e0ecp1c6024jsn0e148e1c8e2b\"")
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
    androidTestImplementation(libs.com.google.dagger.hilt.android.testing)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    kaptAndroidTest(libs.com.google.dagger.hilt.android.compiler)

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

    // Instrumentation tests
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Local unit tests
    testImplementation(libs.androidx.test.ext.junit)
}