plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt") // Enables annotation processing for Room
    id("kotlin-parcelize") // For Parcelable support
}

android {
    namespace = "com.example.notesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.notesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true // Enables View Binding
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17" // Specifies Kotlin JVM target
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx) // Core Kotlin extensions
    implementation(libs.androidx.appcompat) // AppCompat support library
    implementation(libs.material) // Material Design components
    implementation(libs.androidx.activity) // Activity support
    implementation(libs.androidx.constraintlayout) // ConstraintLayout support

    // Room (for SQLite database support)
    implementation("androidx.room:room-runtime:2.6.1") // Runtime library
    implementation("androidx.room:room-ktx:2.6.1") // Kotlin extensions
    kapt("androidx.room:room-compiler:2.6.1") // Annotation processor

    // Lifecycle components (ViewModel, LiveData, etc.)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") // ViewModel KTX
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // Lifecycle runtime KTX

    // Kotlin Coroutines (for asynchronous programming)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Coroutines for Android

    // Testing dependencies
    testImplementation(libs.junit) // JUnit for unit testing
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit for Android tests
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing
}
