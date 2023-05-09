buildscript {
    dependencies {
        classpath(Deps.android_gradle_plugin)
        classpath(Deps.kotlin_gradle_plugin)
    }
}

plugins {
    id(androidApp).version(Versions.android_gradle_plugin).apply(false)
    id(androidLib).version(Versions.android_gradle_plugin).apply(false)
    kotlin(androidPlugin).version(Versions.kotlin).apply(false)
    kotlin(multiplatform).version(Versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
