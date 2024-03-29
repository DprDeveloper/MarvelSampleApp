import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "es.dpr.marvelsampleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "es.dpr.marvelsampleapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val localProperties: Properties = gradleLocalProperties(rootDir)
        buildConfigField(
            type = "String",
            name = "MARVEL_PRIVATE_KEY",
            value = localProperties.getProperty("MARVEL_PRIVATE_KEY") ?: "")
        buildConfigField(
            type = "String",
            name = "MARVEL_PUBLIC_KEY",
            value = localProperties.getProperty("MARVEL_PUBLIC_KEY") ?: "")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    var retrofit = "2.9.0"
    var gson = "2.9.0"
    var okhttp = "4.9.2"
    var hilt = "2.50"
    var hilt_navigation ="1.1.0"
    var coil = "2.5.0"
    var lottie = "4.0.0"
    var navigation = "2.5.3"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    //Gson
    implementation("com.squareup.retrofit2:converter-gson:$gson")
    //OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp")
    //Hilt
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_navigation")
    implementation("com.google.dagger:hilt-android:$hilt")
    kapt("com.google.dagger:hilt-android-compiler:$hilt")
    //Coil
    implementation("io.coil-kt:coil-compose:$coil")
    //lottie
    implementation("com.airbnb.android:lottie-compose:$lottie")
    //Navigation
    implementation("androidx.navigation:navigation-compose:$navigation")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}