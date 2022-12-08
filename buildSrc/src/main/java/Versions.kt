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
}