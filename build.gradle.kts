// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.kotlin.gradle.plugin)
        classpath(libs.com.google.gms.google.services)
        classpath(libs.com.google.dagger.hilt.android.gradle.plugin)
        classpath(libs.androidx.navigation.navigation.safe.args.gradle.plugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.43.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}

apply("${project.rootDir}/scripts/toml-updater-config.gradle")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}