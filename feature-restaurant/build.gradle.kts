apply(mapOf("plugin" to "androidx.navigation.safeargs.kotlin"))

android {
    buildFeatures {
        dataBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    with(Dependencies.Module) {
        implementation(project(shared))
        implementation(project(core_data))
        implementation(project(core_domain))
    }

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }
    Dependencies.AndroidUI().forEach { implementation(it) }
    Dependencies.AndroidLifecycle().forEach { implementation(it) }

    with(Dependencies.Coroutines) {
        implementation(core)
        testImplementation(testing)
    }

    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
        testImplementation(test)
    }

    with(Dependencies.Navigation) {
        implementation(fragment)
        implementation(ui)
        androidTestImplementation(testing)
    }

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}
