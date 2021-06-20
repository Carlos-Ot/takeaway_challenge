dependencies {
    Dependencies.Kotlin().forEach { implementation(it) }
    implementation(Dependencies.Koin.core)

    with(Dependencies.AndroidUI) {
        implementation(appCompat)
        implementation(fragment)
        implementation(recyclerView)
        implementation(material)
    }

    implementation(Dependencies.AndroidLifecycle.livedata)
}