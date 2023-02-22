plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.example.tcmusic.core.network"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:common"))

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.android)
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Retrofit & Okhttp
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.retrofit2.converter.gson)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)

    // Json
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)
}