plugins {
    // jvmToolchain で指定した JDK が手元に無いとき、自動で取得する
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "kotlin-core"

// 課題ごとに 1 つのサブプロジェクトにする。プロジェクト名は課題番号（例: ./gradlew :b02:test）
val taskDirs = mapOf(
    "b01" to "basic/b01-gradle",
    "b02" to "basic/b02-syntax-and-types",
    "b03" to "basic/b03-control-flow",
    "b04" to "basic/b04-functions-and-lambdas",
    "b05" to "basic/b05-testing",
)
taskDirs.forEach { (name, dir) ->
    include(":$name")
    project(":$name").projectDir = file(dir)
}
