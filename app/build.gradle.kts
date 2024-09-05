plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.example.power_email_client"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.power_email_client"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments.put("clearPackageData", "true") // Revoke permissions and clear app state after each instrumented test

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Android Core
    implementation(libs.androidx.core.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Material Design
    implementation(libs.androidx.material3)
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation(libs.androidx.material.icons.extended)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Kotlin
    implementation(libs.kotlin.collections.immutable)

    // Serialization
    implementation(libs.kotlin.serialization.json)

    // Local Tests
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)

    // Instrumented Tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit.ui.test)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestUtil(libs.androidx.orchestrator)

    // Other
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}