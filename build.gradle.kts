plugins {
    // Kotlin の Gradle プラグインを、バージョンを 1 か所（gradle/libs.versions.toml）で決めて読み込む。
    // 各課題の build.gradle.kts は、ここで読み込んだものを alias(libs.plugins.kotlin.jvm) で使う
    alias(libs.plugins.kotlin.jvm) apply false
}
