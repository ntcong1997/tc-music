plugins {
    id("tcmusic.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.example.tcmusic.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:player"))
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.android)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.android.compiler)
}