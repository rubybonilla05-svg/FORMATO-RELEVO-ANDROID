plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.formato.relevo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.formato.relevo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
