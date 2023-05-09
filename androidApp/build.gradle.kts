import Deps.Compose.activity
import Deps.Compose.compiler
import Deps.Compose.foundation
import Deps.Compose.foundation_layout
import Deps.Compose.material
import Deps.Compose.material_icons_extended
import Deps.Compose.runtime
import Deps.Compose.runtime_livedata
import Deps.Compose.ui
import Deps.Compose.uiTooling
import Deps.Compose.uiToolingPreview
import Versions.compile_sdk
import Versions.compose_compiler_version
import Versions.min_sdk
import Versions.target_sdk

plugins {
    id(androidApp)
    kotlin(androidPlugin)
}

android {
    namespace = "com.alextos.findtime.android"
    compileSdk = compile_sdk
    defaultConfig {
        applicationId = "com.alextos.findtime.android"
        minSdk = min_sdk
        targetSdk = target_sdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_compiler_version
    }
    packagingOptions {
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    with(Deps) {
        implementation(material)
        implementation(napier)
    }

    implementation(compiler)
    implementation(runtime)
    implementation(runtime_livedata)
    implementation(ui)
    implementation(uiTooling)
    implementation(uiToolingPreview)
    implementation(foundation)
    implementation(foundation_layout)
    implementation(material)
    implementation(material_icons_extended)
    implementation(activity)
}