plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.common"
}

dependencies {
    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.android)
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Turbine
    testImplementation(libs.app.cash.turbine)
}