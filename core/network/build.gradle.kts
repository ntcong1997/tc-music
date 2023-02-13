plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.example.tcmusic.core.network"
}

dependencies {
    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core)
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Retrofit & Okhttp
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.retrofit2.converter.gson)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
}