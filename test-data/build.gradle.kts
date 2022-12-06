plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(platform(project(":depconstraints")))
    implementation(project(":model"))

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)
}