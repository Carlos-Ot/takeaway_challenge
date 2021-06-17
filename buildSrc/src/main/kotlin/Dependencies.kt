import kotlin.reflect.full.memberProperties

object Dependencies {

    object Module {
        const val core_local_storage = ":core-local-storage"
        const val core_data = ":core-data"
    }

    val modules: List<String> by lazy {
        Module::class.memberProperties.map {
            it.name.replace("_", "-")
        }
    }

    object Kotlin {
        private const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
        private const val coreJDK7 =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

        operator fun invoke() = arrayOf(core, coreJDK7)
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinVersion}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinVersion}"
        const val testing =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinVersion}"
    }

    object Koin {
        private const val core = "org.koin:koin-core:${Versions.DI.koin}"
        private const val coreExt = "org.koin:koin-core-ext:${Versions.DI.koin}"

        operator fun invoke() = arrayOf(core, coreExt)
    }

    object KoinAndroid {
        private const val android = "org.koin:koin-android:${Versions.DI.koin}"
        private const val androidViewModel = "org.koin:koin-android-viewmodel:${Versions.DI.koin}"

        operator fun invoke() = arrayOf(android, androidViewModel)
    }

    object AndroidUI {
        private const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidUI.appcompat}"
        private const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidUI.constraintLayout}"
        private const val material =
            "com.google.android.material:material:${Versions.AndroidUI.material}"
        private const val fragment =
            "androidx.fragment:fragment-ktx:${Versions.AndroidKTX.fragment}"

        operator fun invoke() = arrayOf(appCompat, constraintLayout, material, fragment)
    }

    object AndroidX {
        private const val core = "androidx.core:core-ktx:${Versions.AndroidKTX.core}"
        private const val annotation =
            "androidx.annotation:annotation:${Versions.AndroidUI.annotation}"

        operator fun invoke() = arrayOf(core, annotation)
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.AndroidJetPack.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.AndroidJetPack.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.AndroidJetPack.room}"
        const val testing = "androidx.room:room-testing:${Versions.AndroidJetPack.room}"
    }

    object Moshi {
        private const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        private const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

        operator fun invoke() = arrayOf(moshi, codeGen)
    }

    object AndroidLifecycle {
        private const val extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidJetPack.lifecycle}"
        private const val livedata =
            "androidx.lifecycle:lifecycle-livedata:${Versions.AndroidJetPack.lifecycle}"
        private const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidJetPack.lifecycle}"
        private const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidJetPack.lifecycle}"

        const val compiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidJetPack.lifecycle}"

        operator fun invoke() = arrayOf(extensions, livedata, livedataKtx, viewModelKtx)
    }

    object Lint {
        const val detekt = "io.gitlab.arturbosch.detekt"
        const val ktLint = "org.jlleitschuh.gradle.ktlint"
    }

    object Testing {

        // Unit Testing
        private const val jUnit = "junit:junit:${Versions.Testing.junit}"
        private const val archCore =
            "androidx.arch.core:core-testing:${Versions.AndroidJetPack.arch_core}"
        private const val kotlinJUnit =
            "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
        private const val mockk = "io.mockk:mockk:${Versions.Testing.mockk}"
        private const val truth = "com.google.truth:truth:${Versions.Testing.truth}"

        // Android Testing
        private const val androidTestCore = "androidx.test:core:${Versions.Testing.androidTestCore}"
        private const val androidJUnit =
            "androidx.test.ext:junit-ktx:${Versions.Testing.androidTestJUnit}"
        private const val testRunner = "androidx.test:runner:${Versions.Testing.androidTestCore}"
        private const val testRules = "androidx.test:rules:${Versions.Testing.androidTestCore}"

        operator fun invoke(type: Type) = when (type) {
            Type.UNIT -> arrayOf(jUnit, archCore, kotlinJUnit, mockk, truth)
            Type.ANDROID -> arrayOf(
                androidTestCore,
                archCore,
                androidJUnit,
                testRunner,
                testRules,
                truth
            )
        }

        enum class Type {
            UNIT,
            ANDROID
        }
    }
}