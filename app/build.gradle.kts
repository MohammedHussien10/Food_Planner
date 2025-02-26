plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodplanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodplanner"
        minSdk = 30
        targetSdk = 35
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

   buildFeatures {    viewBinding = true}
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gms.play.services.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //firebase
    implementation(libs.firebase.auth)
    implementation (libs.firebase.ui.auth)
   // implementation(libs.play.services.auth)
    //retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    //glide
    implementation (libs.glide)
    //lottie
    implementation (libs.lottie)
    //navigation
    implementation (libs.navigation.fragment)
    implementation (libs.navigation.ui)
    //material design
    implementation (libs.material.v1110)
    implementation ("com.google.android.material:material:1.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //RX
    implementation (libs.rxandroid)
    implementation (libs.rxjava)
    implementation (libs.rxjava3.retrofit.adapter)
    implementation("androidx.room:room-rxjava3:2.6.1")

    //Youtube
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

}