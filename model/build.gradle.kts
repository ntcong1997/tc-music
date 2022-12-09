plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)

    // Utils
    implementation(libs.com.google.code.gson)
}