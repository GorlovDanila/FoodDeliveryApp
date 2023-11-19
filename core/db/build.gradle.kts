plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    alias(libs.plugins.jetbrainsKotlinKapt)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.core.db"
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.room)
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.room.kapt)
}