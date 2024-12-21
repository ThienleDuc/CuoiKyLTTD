plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ktck124.lop124LTDD04.nhom17"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ktck124.lop124LTDD04.nhom17"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    //noinspection GradlePath
    implementation(files("libs\\jtds-1.3.1.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //noinspection UseTomlInstead
    implementation("com.microsoft.sqlserver:mssql-jdbc:9.4.1.jre8")
    //noinspection UseTomlInstead
    implementation("com.squareup.picasso:picasso:2.8")
}