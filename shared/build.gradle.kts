dependencies {
    Dependencies.Kotlin().forEach { implementation(it) }
    implementation(Dependencies.Koin.core)

    with(Dependencies.AndroidUI) {
        implementation(appCompat)
        implementation(fragment)
        implementation(recyclerView)
        implementation(material)
    }

    with(Dependencies.Navigation) {
        implementation(fragment)
        implementation(ui)
        androidTestImplementation(testing)
    }

    implementation(Dependencies.AndroidLifecycle.livedata)
}
