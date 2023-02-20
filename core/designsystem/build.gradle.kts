plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.core.designsystem"
}

dependencies {
    // Core
    implementation(libs.androidx.core.core.ktx)

    // Compose
    api(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.material.icons.extended)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.ui.tooling)
    api(libs.androidx.compose.ui.ui.tooling.preview)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
}