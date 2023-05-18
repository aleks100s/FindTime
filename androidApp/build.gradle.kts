plugins {
    id(androidApp)
    kotlin(androidPlugin)
}

android {
    namespace = "com.alextos.findtime.android"
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "com.alextos.findtime.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))

    with(Deps) {
        implementation(material)
        implementation(napier)
    }

    implementation(Deps.Compose.compiler)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.runtime_livedata)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.uiToolingPreview)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.foundation_layout)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material_icons_extended)
    implementation(Deps.Compose.activity)
}