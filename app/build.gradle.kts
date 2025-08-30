// app/build.gradle.kts

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // id("com.google.gms.google-services") // enable only if google-services.json exists
}

// ---- Signing props pulled from gradle.properties (with safe fallbacks) ----
val RELEASE_STORE_FILE: String =
    (project.findProperty("RELEASE_STORE_FILE") as String?) ?: "app/keystore/ziioz-upload.jks"
val RELEASE_STORE_PASSWORD: String =
    (project.findProperty("RELEASE_STORE_PASSWORD") as String?) ?: ""
val RELEASE_KEY_ALIAS: String =
    (project.findProperty("RELEASE_KEY_ALIAS") as String?) ?: "ziioz-upload"
val RELEASE_KEY_PASSWORD: String =
    (project.findProperty("RELEASE_KEY_PASSWORD") as String?) ?: RELEASE_STORE_PASSWORD

android {
    namespace = "com.ziioz.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ziioz.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 15
        versionName = "1.0.5"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    // ---- SINGLE signingConfigs block (no duplicates) ----
    signingConfigs {
        maybeCreate("release").apply {
            storeFile = file(RELEASE_STORE_FILE)
            storePassword = RELEASE_STORE_PASSWORD
            keyAlias = RELEASE_KEY_ALIAS
            keyPassword = RELEASE_KEY_PASSWORD
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            // Phone -> PC during dev (update PORT if needed)
            buildConfigField("String", "API_BASE_URL", "\"http://127.0.0.1:3000/\"")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Put your real HTTPS prod URL when ready
            buildConfigField("String", "API_BASE_URL", "\"http://127.0.0.1:3000/\"")
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Use ONE Compose BOM
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.01.00"))

    // Core / Activity / Lifecycle
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    // Compose UI (no explicit versions when using BOM)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.animation:animation")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Networking: Retrofit + OkHttp + Moshi
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // Images & video
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    // Browser (for external links)
    implementation("androidx.browser:browser:1.8.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
}
