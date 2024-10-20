plugins {
    alias(libs.plugins.idealista.android.application)
}

android {
    namespace = "com.development.idealistaandroidchallenge"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(projects.ads.presentation)
    implementation(projects.ads.data)

    implementation(libs.bundles.koin)

    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}