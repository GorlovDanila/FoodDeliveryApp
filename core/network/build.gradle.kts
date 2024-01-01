plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.core.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.core)

    api(libs.retrofit.kotlin.serialization)
    api(libs.retrofit.converter.gson)
    api(libs.retrofit.core)
    api(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}