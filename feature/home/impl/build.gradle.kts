plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.feature.home.impl"
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

    implementation(project(":feature:home:api"))
    implementation(project(":feature:cart:api"))

    implementation(project(":core:widget"))
    implementation(project(":core:network"))
    implementation(project(":core:db"))
    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))
    implementation(project(":core:presentation"))

    implementation(libs.koin)
    implementation(libs.koin.compose)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}