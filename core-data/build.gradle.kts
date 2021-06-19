dependencies {
    implementation(project(Dependencies.Module.shared))
    implementation(project(Dependencies.Module.core_local_storage))

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }

    with(Dependencies.Coroutines) {
        implementation(core)
        testImplementation(testing)
    }

    Dependencies.Koin().forEach { implementation(it) }
    testImplementation(Dependencies.Koin.test)

    implementation(Dependencies.AndroidLifecycle.livedata)

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
}