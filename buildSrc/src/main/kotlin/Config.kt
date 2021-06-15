object Config {

    const val applicationId = "com.ottoboni.takeawaychallenge"
    const val compileSdk = 30
    const val minSdkVersion = 26
    const val targetSdkVersion = 30
    const val buildTools = "30.0.3"
    const val versionCode = 1
    const val versionName = "1.0.0"

    object AndroidClassPath {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
        const val kotlinPlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object AndroidTestRunner {
        const val instrumentationTestRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object BuildField {

        object Types {
            const val string = "String"
        }

        object Field {
        }

        object Value {
        }
    }
}