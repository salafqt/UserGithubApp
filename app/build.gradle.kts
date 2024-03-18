plugins {
    id ("kotlin-kapt")
    alias(libs.plugins.gmazzo)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

buildConfig{
    buildConfigField ("String", "API_BASE_URL", "\"https://api.github.com/\"")
    buildConfigField ("String", "API_TOKEN", "\"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\"")
}

android {
    namespace = "com.bangkit.usergithub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bangkit.usergithub"
        minSdk = 33
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("androidx.activity:activity-ktx:1.8.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}