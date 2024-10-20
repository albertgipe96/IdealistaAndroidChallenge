plugins {
    alias(libs.plugins.idealista.android.library)
    alias(libs.plugins.idealista.ktor)
}

android {
    namespace = "com.development.core.data"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.bundles.koin)

    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}