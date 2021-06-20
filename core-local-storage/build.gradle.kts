dependencies {
    implementation(project(Dependencies.Module.shared))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }

    with(Dependencies.Coroutines) {
        implementation(core)
        testImplementation(testing)
    }

    with(Dependencies.Room) {
        implementation(runtime)
        implementation(ktx)
        kapt(compiler)
        testImplementation(testing)
    }

    Dependencies.Moshi().forEach { implementation(it) }

    implementation(Dependencies.AndroidLifecycle.livedata)

    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
        testImplementation(test)
    }

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}
