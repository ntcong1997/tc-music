plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":model"))

    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)
}