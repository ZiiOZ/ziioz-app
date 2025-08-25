// app/build.gradle.kts (Module: app)

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // id("com.google.gms.google-services") // enable only after google-services.json exists
}

val ziiStorePath = (project.findProperty("ZII_KEYSTORE") ?: "keystore/ziioz-upload.jks").toString()
val ziiStorePass = (project.findProperty("ZII_KEYSTORE_PASSWORD") ?: "").toString()
val ziiKeyAlias  = (project.findProperty("ZII_KEY_ALIAS") ?: "upload").toString()
val ziiKeyPass   = (project.findProperty("ZII_KEY_PASSWORD") ?: ziiStorePass).toString()

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
    composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }

    signingConfigs {
        create("release") {
            storeFile = file(ziiStorePath)
            storePassword = ziiStorePass
            keyAlias = ziiKeyAlias
            keyPassword = ziiKeyPass
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
    maven(url = "https://dl.google.com/dl/android/maven2/")
    maven(url = "https://maven.google.com")
}

dependencies {
    // --- Compose / AndroidX ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-saveable")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    // --- Networking: Retrofit + OkHttp + Gson ---
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")

    // --- Extras ---
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.browser:browser:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    testImplementation("junit:junit:4.13.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
}
