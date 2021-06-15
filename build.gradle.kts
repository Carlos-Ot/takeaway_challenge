buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Config.AndroidClassPath.gradlePlugin)
        classpath(Config.AndroidClassPath.kotlinPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("${rootProject.buildDir}")
}