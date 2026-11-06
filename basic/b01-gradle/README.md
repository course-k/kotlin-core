# b01 Gradle のプロジェクトを作って動かす

## 位置

基礎 b01（全 20 課題の 1 番目）。前の課題: なし。次の課題: b02 基本の構文と型。

## 終えるとできること

- Kotlin のプログラムを Gradle のサブプロジェクトとして書き、コマンド 1 つで実行できる。
- Gradle でテストを走らせ、結果を読める。
- ビルドスクリプト（`build.gradle.kts`）の各ブロックが何を決めているかを説明できる。

## 扱う能力領域

L19（Gradle: Kotlin DSL、依存の宣言、Version Catalog、タスクの実行）

## 初めて使う道具

### JDK

- **何をする道具か**: Java の仮想マシン（JVM）と、JVM 向けのツール一式。Kotlin のコードは JVM のバイトコードにコンパイルされ、JVM の上で動く。
- **なぜこの計画で要るか**: この計画のサーバーサイド Kotlin は、すべて JVM の上で動かす。
- **用意するもの**: Gradle 自体を動かすために JDK 17 以上を 1 つ入れ、環境変数 `JAVA_HOME` にその場所を設定する。コンパイルと実行に使う JDK 25 は、Gradle が自動で取得する（ルートの `settings.gradle.kts` の foojay プラグイン）。

### Gradle と Gradle Wrapper

- **何をする道具か**: ビルドツール。依存ライブラリの取得、コンパイル、テストの実行、アプリケーションの実行を、「タスク」という単位で行う。
- **なぜこの計画で要るか**: Kotlin 公式ドキュメントのサーバーサイドの手順は Gradle を前提にしている。この教材リポジトリでは、課題ごとに 1 つの Gradle サブプロジェクトを作り、テストで合否を判定する。
- **Gradle Wrapper**: リポジトリのルートにある `./gradlew` は、決まったバージョンの Gradle（`gradle/wrapper/gradle-wrapper.properties` に記載）を取得して実行するスクリプト。Gradle をインストールしなくても、全員が同じバージョンで動かせる。
- **コマンドの形**: `./gradlew :<プロジェクト名>:<タスク名>`。プロジェクト名は課題番号（この課題は `b01`）。コマンドはリポジトリのルートで実行する。

| コマンド | すること |
|---|---|
| `./gradlew :b01:run` | `application` プラグインの `run` タスク。指定したメインクラスを実行する |
| `./gradlew :b01:test` | `test` タスク。`src/test/kotlin` のテストを走らせる |
| `./gradlew -q :b01:run` | `-q` で Gradle 自身の表示を減らし、プログラムの出力だけを見る |

### この教材リポジトリの骨組み（用意済み）

| ファイル | 決めていること |
|---|---|
| `settings.gradle.kts` | どのディレクトリをサブプロジェクトにするか。課題番号とディレクトリの対応 |
| `gradle/libs.versions.toml` | Version Catalog。使うライブラリとプラグインのバージョンを 1 か所で決める |
| ルートの `build.gradle.kts` | Kotlin の Gradle プラグインを全サブプロジェクトで同じバージョンにする |

## やること

この課題のディレクトリは `basic/b01-gradle/`。テスト `src/test/kotlin/MainTest.kt` は用意してある。

### 書き写し

1. `basic/b01-gradle/build.gradle.kts` を作り、手本 1 を打ち込む。
2. `basic/b01-gradle/src/main/kotlin/Main.kt` を作り、手本 2 を打ち込む。
3. `./gradlew :b01:run` を実行し、`Hello world!` が出ることを確かめる。初回は Gradle 本体と JDK 25 の取得に数分かかることがあり、`-q` を付けないと進み具合が表示される。
4. `./gradlew :b01:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。
5. `ANSWERS.md` の「書き写し」の問いに、自分の言葉で答える。

**手本 1**（`build.gradle.kts`）

```kotlin
plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}
```

書きながら見る書き方:

- `plugins { }` は、このプロジェクトで使う Gradle プラグインを並べる。`alias(libs.plugins.kotlin.jvm)` は、Version Catalog の `[plugins]` の `kotlin-jvm` を指す（`-` は `.` で書く）。
- `repositories { }` は、依存ライブラリをどこから取るかを決める。
- `dependencies { }` の `testImplementation` は、テストのコンパイルと実行にだけ使う依存を宣言する。`kotlin("test")` は Kotlin の標準のテストライブラリ kotlin.test。
- `kotlin { jvmToolchain(25) }` は、コンパイルと実行に使う JDK のバージョンを決める。
- `application { mainClass.set("MainKt") }` は、`run` タスクで実行するクラスを決める。
- `tasks.test { useJUnitPlatform() }` は、テストを JUnit Platform の上で走らせる。この設定があると、`kotlin("test")` は JUnit 5 用の kotlin-test-junit5 に解決される。

出典から変えたところ: 行末の説明のコメント（`// Kotlin version to use` など）を外した。公式チュートリアルの 1 行目 `kotlin("jvm") version "..."` を、Version Catalog を使う `alias(libs.plugins.kotlin.jvm)` に置き換えた。`application` の 2 か所は、公式チュートリアル「Test code using JUnit in JVM」のビルドスクリプトから取った。

**手本 2**（`src/main/kotlin/Main.kt`）

```kotlin
fun main() {
    println("Hello world!")
}
```

書きながら見る書き方:

- `fun main()` がプログラムの入口。クラスで包まずに、ファイルの直下（トップレベル）に書ける。
- `println` は引数を標準出力に書き、改行する。

### 確認課題

6. `Main.kt` のファイル名を `App.kt` に変える。
7. `./gradlew -q :b01:run` を実行し、失敗することを確かめる。エラーのうち `ClassNotFoundException` を含む行を `ANSWERS.md` に写す（その前の行は、OS の言語設定によって日本語で表示される）。
8. `build.gradle.kts` を直して、`./gradlew -q :b01:run` で再び `Hello world!` が出るようにする。
9. `./gradlew :b01:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。
10. `ANSWERS.md` の「確認課題」の問いに答える。

## 合格の基準

| 対象 | 基準 | 判定 |
|---|---|---|
| 書き写し | 手本が動く。`ANSWERS.md` の記述が模範解説と照合して要点を押さえている | AI |
| 確認課題 | `./gradlew -q :b01:run` が `Hello world!` を出し、`./gradlew :b01:test` が通る | テストと実行結果 |

AI の判定を受けるときは、この `README.md`、`ANSWERS.md`、`EXPLANATION.md` のパスを AI に渡す。`EXPLANATION.md` は、`ANSWERS.md` を書き終えてから読む。

## 出典

- Get started with Gradle and Kotlin/JVM: https://kotlinlang.org/docs/get-started-with-jvm-gradle-project.html
- Test code using JUnit in JVM（`application` の設定）: https://kotlinlang.org/docs/jvm-test-using-junit.html
- Basic syntax（Program entry point）: https://kotlinlang.org/docs/basic-syntax.html#program-entry-point
- Gradle（Kotlin の Gradle の設定の全体）: https://kotlinlang.org/docs/gradle.html
- Configure a Gradle project（kotlin.test の JUnit 版の選ばれ方）: https://kotlinlang.org/docs/gradle-configure-project.html#set-dependencies-on-test-libraries
- Calling Kotlin from Java（トップレベル関数が入るクラスの名前）: https://kotlinlang.org/docs/java-to-kotlin-interop.html#package-level-functions
- Gradle の Application プラグイン: https://docs.gradle.org/current/userguide/application_plugin.html
- Gradle の Version Catalog: https://docs.gradle.org/current/userguide/version_catalogs.html
- Gradle Wrapper: https://docs.gradle.org/current/userguide/gradle_wrapper.html
