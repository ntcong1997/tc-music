plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.testing"
}

dependencies {
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Test runner
    implementation(libs.androidx.test.runner)

    // Hilt test
    implementation(libs.com.google.dagger.hilt.android.testing)

    // Local unit tests
    implementation(libs.junit)
}