const val androidPlugin = "android"
const val androidApp = "com.android.application"
const val androidLib = "com.android.library"
const val multiplatform = "multiplatform"
const val composePlugin = "org.jetbrains.compose"

object Versions {
    const val min_sdk = 24
    const val target_sdk = 33
    const val compile_sdk = 33

    const val kotlin = "1.8.20"
    const val kotlin_gradle_plugin = "1.8.20"
    const val android_gradle_plugin = "8.0.1"
    const val desktop_compose_plugin = "1.4.0"
    const val compose_compiler_version = "1.4.6"
    const val compose_version = "1.3.1"

    const val coroutines = "1.7.0"
    const val junit = "4.13.2"
    const val material = "1.9.0"
    const val kotlinxDateTime = "0.4.0"
    const val activityCompose = "1.7.1"
    const val napier = "2.6.1"
    const val junit5 = "1.5.10"
    const val frameworkName = "shared"
}

object Deps {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin}"

    const val junit = "junit:junit:${Versions.junit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val napier = "io.github.aakira:napier:${Versions.napier}"

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose_version}"
        const val uiUtil = "androidx.compose.ui:ui-util:${Versions.compose_version}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
        const val material = "androidx.compose.material:material:${Versions.compose_version}"
        const val material_icons_extended = "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose_version}"
        const val compiler = "androidx.compose.compiler:compiler:${Versions.compose_version}"
        const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
        const val foundation_layout = "androidx.compose.foundation:foundation-layout:${Versions.compose_version}"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object JetBrains {
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
        const val uiDesktop = "org.jetbrains.compose.ui:ui-desktop:${Versions.compose_version}"
        const val uiUtil = "org.jetbrains.compose.ui:ui-util:${Versions.compose_version}"
    }
}