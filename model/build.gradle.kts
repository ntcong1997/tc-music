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

    // Utils
    implementation(Libs.GSON)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Local unit tests
    testImplementation(Libs.JUNIT)
}