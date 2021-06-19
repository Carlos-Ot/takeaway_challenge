android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Dependencies.Module.shared))
    implementation(project(Dependencies.Module.core_data))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }
    Dependencies.AndroidUI().forEach { implementation(it) }
    Dependencies.AndroidLifecycle().forEach { implementation(it) }

    Dependencies.Koin().forEach { implementation(it) }
    Dependencies.KoinAndroid().forEach { implementation(it) }
    testImplementation(Dependencies.Koin.test)

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}
