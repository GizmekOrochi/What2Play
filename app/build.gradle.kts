plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.what2play"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.what2play"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")
    // Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

    // Test helpers
    testImplementation("androidx.room:room-testing:$room_version")
}