dependencies {

    Dependencies.Kotlin().forEach { implementation(it) }
    Dependencies.AndroidX().forEach { implementation(it) }

    with(Dependencies.Room) {
        implementation(runtime)
        implementation(ktx)
        kapt(compiler)
        testImplementation(testing)
    }

    Dependencies.Moshi().forEach { implementation(it) }

    Dependencies.Koin().forEach { implementation(it) }

    Dependencies.Testing(Dependencies.Testing.Type.UNIT).forEach { testImplementation(it) }
    Dependencies.Testing(Dependencies.Testing.Type.ANDROID)
        .forEach { androidTestImplementation(it) }
}