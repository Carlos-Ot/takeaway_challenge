import Dependencies.modules
import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Config.AndroidClassPath.gradlePlugin)
        classpath(Config.AndroidClassPath.kotlinPlugin)
        classpath(Config.AndroidClassPath.navigation)
    }
}

plugins {
    id(Dependencies.Lint.detekt).version(Versions.Lint.detekt)
    id(Dependencies.Lint.ktLint).version(Versions.Lint.ktLintPlugin)
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        flatDir {
            dirs("libs")
        }
    }
    setupModules()
}

fun Project.setupModules() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppModule()
        modules.contains(name) -> configureLibraryModules()
    }
}

fun Project.configureLibraryModules() {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-parcelize")

    detekt {
        failFast = true
        buildUponDefaultConfig = true

        reports {
            xml {
                enabled = true
                destination = file("$projectDir/build/reports/detekt/detektReport.xml")
            }
            html {
                enabled = true
                destination = file("$projectDir/build/reports/detekt/detektReport.html")
            }
        }
    }

    ktlint {
        version.set(Versions.Lint.ktLint)
        android.set(true)
        ignoreFailures.set(true)
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
    }

    configure<BaseExtension> {
        compileSdkVersion(Config.compileSdk)
        buildToolsVersion(Config.buildTools)
        testOptions.unitTests.isIncludeAndroidResources = true

        defaultConfig {
            minSdkVersion(Config.minSdkVersion)
            targetSdkVersion(Config.targetSdkVersion)
            testInstrumentationRunner(Config.AndroidTestRunner.instrumentationTestRunner)
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                isDebuggable = true
            }

            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        sourceSets {
            getByName("test").resources.srcDirs("../sampledata")
            getByName("androidTest").resources.srcDirs("../sampledata")
        }

        lintOptions {
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/LICENSE")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/license.txt")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/notice.txt")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/*.kotlin_module")
        }
    }
}

fun Project.configureAppModule() {
    apply(plugin = "com.android.application")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
//    apply(plugin = "androidx.navigation.safeargs.kotlin")

    configure<BaseExtension> {
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

        testOptions {
            animationsDisabled = true
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                isDebuggable = true
            }

            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        sourceSets {
            getByName("androidTest").java.srcDirs("src")
        }

        lintOptions {
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
            targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = org.gradle.api.JavaVersion.VERSION_1_8.toString()
            }
        }

        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/LICENSE")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/license.txt")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/notice.txt")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/*.kotlin_module")
        }
    }
}

tasks.register("clean").configure {
    delete("${rootProject.buildDir}")
}
