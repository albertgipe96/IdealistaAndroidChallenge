plugins {
    `kotlin-dsl`
}

group = "com.development.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "idealista.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "idealista.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "idealista.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("ktor") {
            id = "idealista.ktor"
            implementationClass = "KtorConventionPlugin"
        }
    }
}