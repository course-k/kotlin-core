# b01 解答

## 書き写し

1. `kotlin("test")` を、`implementation` ではなく `testImplementation` で宣言しているのはなぜか。
   -
2. `jvmToolchain(25)` は何を決めているか。`JAVA_HOME` に設定した JDK とは、使われる場面がどう違うか。
   -
3. `alias(libs.plugins.kotlin.jvm)` の `libs.plugins.kotlin.jvm` は、どのファイルのどこを指しているか。プラグインのバージョンを 1 か所で決めると何がよいか。
   -
4. `Main.kt` の `main` は、Java の `main` と比べて、どこに書いているか。`./gradlew :b01:run` を実行した後、`basic/b01-gradle/build/classes/kotlin/main/` にできたクラスファイルの名前は何か。その名前は `Main.kt` とどう対応しているか。
   -

## 確認課題

1. 手順 7 のエラーのうち `ClassNotFoundException` を含む行:
   -
2. 何を直したか。なぜそれで動くようになったか。
   -
