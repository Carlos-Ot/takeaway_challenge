dependencies {
    Dependencies.Kotlin().forEach { implementation(it) }

    implementation(Dependencies.AndroidLifecycle.livedata)
}