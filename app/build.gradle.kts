android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Dependencies.Module.shared))
    implementation(project(Dependencies.Module.core_local_storage))
    implementation(project(Dependencies.Module.core_data))
    implementation(project(Dependencies.Module.core_domain))
    implementation(project(Dependencies.Module.feature_restaurant))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }
    Dependencies.AndroidUI().forEach { implementation(it) }
    Dependencies.AndroidLifecycle().forEach { implementation(it) }

    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
        testImplementation(test)
    }

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}
