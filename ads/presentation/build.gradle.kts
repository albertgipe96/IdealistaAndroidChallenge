plugins {
    alias(libs.plugins.idealista.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.development.ui"
}

dependencies {
    implementation(projects.ads.domain)

    implementation(projects.core.domain)

    implementation(libs.bundles.koin)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}