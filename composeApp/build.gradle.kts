import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    kotlin("plugin.serialization") version libs.versions.kotlin
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            // Coil
            implementation(libs.coil.network.okHttp)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Navigation https://developer.android.com/develop/ui/compose/navigation#kts
            implementation(libs.compose.navigation)

            //Compose UI Preview https://kotlinlang.org/docs/multiplatform/compose-previews.html#preview-setup
            implementation(libs.compose.uiToolingPreview)
            implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.10.0")

            //ViewModel https://developer.android.com/kotlin/multiplatform/viewmodel
            api(libs.androidx.lifecycle.viewmodel)
            // Icons
            implementation(libs.material.icons.core)
            // Coil
            implementation(libs.coil.compose)

        }
        iosMain.dependencies {
            implementation(libs.coil.network.ktor)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.coil.compose)
//    implementation(libs.coil.network)

    implementation(libs.compose.navigation)

    debugImplementation(libs.compose.uiTooling)
    // Compose UI preview https://kotlinlang.org/docs/multiplatform/compose-previews.html#supported-configurations
    debugImplementation("org.jetbrains.compose.ui:ui-tooling:1.10.0")
}

