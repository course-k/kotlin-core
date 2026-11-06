# b01 模範解説

`ANSWERS.md` を書き終えてから読む。

## なぜこう書くか

### 手本 1（`build.gradle.kts`）

- **`testImplementation`**: 依存の宣言は「どの段階で使うか」ごとに分かれる。`implementation` は本体のコンパイルと実行に使い、`testImplementation` はテストのコンパイルと実行にだけ使う。kotlin.test はテストでしか使わないため、本体の依存に入れない。本体のコンパイルと実行のクラスパスに入らず、`application` プラグインが作る配布物にも入らない。
- **`jvmToolchain(25)`**: コンパイルとテスト・実行に使う JDK のバージョンを固定する。指定が無いと、Gradle を動かしている JDK（`JAVA_HOME` など）がそのまま使われ、人や環境によって JDK が変わる。指定しておくと、Gradle がそのバージョンの JDK を探し、無ければ取得する（ルートの `settings.gradle.kts` の foojay プラグイン）。
- **`alias(libs.plugins.kotlin.jvm)`**: `gradle/libs.versions.toml` の `[plugins]` 節の `kotlin-jvm` を指す。名前の `-` は Kotlin DSL では `.` になる。バージョンは同じファイルの `[versions]` の `kotlin` で決まる。全課題が同じ Kotlin のバージョンを使い、上げるときは 1 行だけ直せばよい。
- **`application` と `mainClass`**: `application` プラグインが `run` タスクを足す。`run` は、`mainClass` に書いたクラスの `main` を実行する。
- **`useJUnitPlatform()`**: Gradle の `test` タスクに、JUnit Platform でテストを探して走らせるよう指示する。Kotlin の Gradle プラグインはこの設定を見て、`kotlin("test")` を JUnit 5 用の kotlin-test-junit5 に解決する。設定が無いと JUnit 4 用が選ばれる。

### 手本 2（`Main.kt`）

- **トップレベル関数**: Kotlin では、関数をクラスの外（ファイルの直下）に書ける。JVM はクラスの中のメソッドしか持たないため、コンパイラはファイルごとにクラスを作り、トップレベル関数をその静的メソッドにする。
- **`MainKt`**: そのクラスの名前は、ファイル名の先頭を大文字にし、`.kt` を `Kt` に変えたもの。`Main.kt` は `MainKt`、`App.kt` は `AppKt` になる。パッケージを宣言したファイルでは、`パッケージ名.MainKt` になる。

### 確認課題

- ファイル名を `App.kt` にすると、クラス名が `AppKt` に変わり、`MainKt` が無くなる。`run` は `MainKt` を探して失敗する（`ClassNotFoundException: MainKt`）。
- `mainClass.set("AppKt")` に直すと動く。
- テストは `main()` を関数として直接呼ぶため、ファイル名を変えても通る。関数の呼び出しはクラス名ではなく、パッケージと関数名で決まる。

## よくある誤り

- `./gradlew` を課題のディレクトリで実行する。`gradlew` はリポジトリのルートにあり、ルートで `./gradlew :b01:run` と書く。
- `mainClass` にファイル名（`Main.kt`）や関数名（`main`）を書く。書くのはクラス名（`MainKt`）。
- `kotlin("test")` を `implementation` に書く。動くが、テスト用のライブラリが本体の依存に入る。

## Java ではこうだが Kotlin では／TypeScript ではこうだが Kotlin では

- **Java**: `main` は `public static void main(String[] args)` として、必ずクラスの中に書く。Kotlin では、トップレベルの `fun main()` をコンパイラがクラス（`MainKt`）の静的メソッドにする。引数 `args` は省略できる。
- **Java**: Gradle の設定は Java でも同じ考え方（プラグイン・リポジトリ・依存・タスク）。Kotlin では `kotlin("jvm")` プラグインと `kotlin { jvmToolchain(..) }` を足す。
- **TypeScript**: `package.json` の `dependencies` と `devDependencies` の分け方は、`implementation` と `testImplementation` の分け方に近い。`npm run <script>` は `./gradlew :<project>:<task>` に当たる。
- **TypeScript**: Node.js はファイルの上から順に実行するが、Kotlin/JVM は入口の `main` 関数から実行する。トップレベルに書けるのは宣言（関数・変数・クラス）だけで、`println(...)` のような文をファイルの直下には書けない。

## 出典

- Get started with Gradle and Kotlin/JVM: https://kotlinlang.org/docs/get-started-with-jvm-gradle-project.html
- Configure a Gradle project（JVM variants of kotlin-test）: https://kotlinlang.org/docs/gradle-configure-project.html#set-dependencies-on-test-libraries
- Calling Kotlin from Java（Package-level functions）: https://kotlinlang.org/docs/java-to-kotlin-interop.html#package-level-functions
- Gradle の依存の構成（implementation と testImplementation）: https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management
