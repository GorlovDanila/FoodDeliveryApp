plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.feature.profile.impl"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompiler.get()
    }
}

dependencies {

    implementation(project(":core:navigation"))
    implementation(project(":core:widget"))
    implementation(project(":feature:profile:api"))
    implementation(project(":feature:auth:api"))

    implementation(libs.koin)
    implementation(libs.koin.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.datastore.core)
    implementation(libs.datastore.preferences)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}