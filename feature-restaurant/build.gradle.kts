apply(mapOf("plugin" to "androidx.navigation.safeargs.kotlin"))

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Dependencies.Module.shared))
    implementation(project(Dependencies.Module.core_domain))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }
    Dependencies.AndroidUI().forEach { implementation(it) }
    Dependencies.AndroidLifecycle().forEach { implementation(it) }
    with(Dependencies.Coroutines) {
        implementation(core)
        testImplementation(testing)
    }

    Dependencies.Koin().forEach { implementation(it) }
    testImplementation(Dependencies.Koin.test)

    with(Dependencies.Navigation) {
        implementation(fragment)
        implementation(ui)
        androidTestImplementation(testing)
    }

    implementation(Dependencies.UI.shimmer)

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}