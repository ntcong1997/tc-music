/**
 * Created by TC on 09/10/2022.
 */

object Versions {
    val versionName = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    private val versionCodeBase = 100000 // XYYZZM; M = Module (tv, mobile)
    val versionCode = versionCodeBase + 3

    const val COMPILE_SDK = 33
    const val TARGET_SDK = 33
    const val MIN_SDK = 24

    const val ANDROID_GRADLE_PLUGIN = "7.2.2"
    const val COMPOSE = "1.2.1"
    const val HILT = "2.44"
    const val KOTLIN = "1.7.20"
    const val GOOGLE_SERVICES = "4.3.13"
    const val NAVIGATION = "2.5.2"
}