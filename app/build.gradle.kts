plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.fooddeliveryapp"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.fooddeliveryapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:di"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:db"))
    implementation(project(":feature:home:impl"))
    implementation(project(":feature:cart:impl"))
    implementation(project(":feature:search:impl"))
    implementation(project(":feature:profile:impl"))
    implementation(project(":core:widget"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.timber)
    implementation(libs.koin)
    implementation(libs.koin.compose)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

detekt {
    source.setFrom(files(projectDir))
    config.setFrom(files("${project.rootDir}/config/detekt/detekt.yml"))
    parallel = true

    reports {
        // Enable/Disable XML report (default: true)
        xml.required.set(false)
        xml.outputLocation.set(file("build/reports/detekt.xml"))
        // Enable/Disable HTML report (default: true)
        html.required.set(true)
        html.outputLocation.set(file("build/reports/detekt.html"))
        // Enable/Disable TXT report (default: true)
        txt.required.set(false)
        txt.outputLocation.set(file("build/reports/detekt.txt"))
        custom {
            // The simple class name of your custom report.
            reportId = "CustomJsonReport"
            outputLocation.set(file("build/reports/detekt.json"))
        }
    }
}
