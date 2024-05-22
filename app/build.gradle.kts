plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.skysoftsolution.in.skill_improvement"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.skysoftsolution.in.skill_improvement"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        ndk {
            abiFilters.add("armeabi-v7a")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("main"){
            jniLibs.srcDirs("jniLibs")
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // If you want to add SweetAlert
    implementation(libs.library)
    implementation(libs.butterknife)
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.tensorflow.lite)
    implementation(libs.face.detection)
    implementation(libs.rxandroid)
    implementation(libs.converter.gson)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.compiler)
    implementation(libs.room.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.guava)

    //for face
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // If you want to add CameraX
    implementation(libs.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.video)
    implementation(libs.camera.view)
    implementation(libs.camera.mlkit.vision)
    implementation(libs.camera.extensions)
    // If you want to add Camera


}
