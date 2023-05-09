buildscript {
    dependencies {
        classpath(Deps.android_gradle_plugin)
        classpath(Deps.kotlin_gradle_plugin)
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.1").apply(false)
    id("com.android.library").version("7.4.1").apply(false)
    kotlin(androidPlugin).version(Versions.kotlin).apply(false)
    kotlin(multiplatform).version(Versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
