import kotlin.reflect.full.memberProperties

object Dependencies {

    object Module {
        const val core_domain = ":core-domain"
        const val core_local_storage = ":core-local-storage"
        const val core_data = ":core-data"
        const val shared = ":shared"
        const val feature_restaurant = ":feature-restaurant"
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
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val testing =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.DI.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.DI.koin}"
        const val androidViewModel = "io.insert-koin:koin-android-viewmodel:${Versions.DI.koin}"
        const val test = "io.insert-koin:koin-test-junit4:${Versions.DI.koin}"
    }

    object AndroidUI {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidUI.appcompat}"
        private const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidUI.constraintLayout}"
        const val material =
            "com.google.android.material:material:${Versions.AndroidUI.material}"
        const val fragment =
            "androidx.fragment:fragment-ktx:${Versions.AndroidKTX.fragment}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidUI.recyclerView}"
        private const val cardView = "androidx.cardview:cardview:${Versions.AndroidUI.cardView}"

        operator fun invoke() =
            arrayOf(appCompat, constraintLayout, material, fragment, recyclerView, cardView)
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val testing = "androidx.navigation:navigation-testing:${Versions.navigation}"
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
        private const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"

        operator fun invoke() = arrayOf(moshi, codeGen, adapters)
    }

    object AndroidLifecycle {
        const val livedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidJetPack.lifecycle}"
        private const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidJetPack.lifecycle}"

        const val compiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidJetPack.lifecycle}"

        const val testing = "androidx.arch.core:core-testing:${Versions.AndroidJetPack.arch_core}"

        operator fun invoke() = arrayOf(livedata, viewModel)
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