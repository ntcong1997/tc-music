plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.core.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:testing"))

    // Core
    implementation(libs.androidx.core.core.ktx)

    // Coil
    implementation(libs.io.coil.kt.coil.compose)

    // Compose
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.ui.tooling)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
}