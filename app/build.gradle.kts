import Config.BuildField.Types

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compileSdk)
    buildToolsVersion(Config.buildTools)

    defaultConfig {
        applicationId(Config.applicationId)
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode(Config.versionCode)
        versionName(Config.versionName)

        testInstrumentationRunner(Config.AndroidTestRunner.instrumentationTestRunner)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core-local-storage"))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }
    Dependencies.AndroidUI().forEach { implementation(it) }
    Dependencies.AndroidLifecycle().forEach { implementation(it) }

    Dependencies.Koin().forEach { implementation(it) }
    Dependencies.KoinAndroid().forEach { implementation(it) }

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}