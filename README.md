# kotlin-core

サーバーサイド Kotlin を身につけるための課題と、その解答のリポジトリ。

- 教材（課題文書・テスト・模範解説・出力予測のコード・解答の枠）は AI が作った。
- 解答（`src/main` のコード、`ANSWERS.md`、b05 以降の自分で書くテスト）は学習者が書いた。
- 課題の合否は、Gradle で走るテストと AI の判定で決める。進み具合は `PROGRESS.md` にある。

## 構成

| パス | 内容 |
|---|---|
| `basic/<課題番号>-<名前>/` | 基礎の課題。`README.md`（課題文書）、`ANSWERS.md`（学習者の記述）、`EXPLANATION.md`（模範解説）、`src/` |
| `intermediate/` | 中級の課題（これから作る） |
| `advanced/` | 応用の題材（これから作る） |
| `exam/<レベル>/<受験の回>/` | 修了試験 |
| `PROGRESS.md` | 課題ごとの合否 |
| `THIRD_PARTY_NOTICES.md` | 書き写した手本の出典とライセンス |

## 動かし方

1. JDK 17 以上を入れ、`JAVA_HOME` を設定する（Gradle を動かすため）。コンパイルと実行に使う JDK 25 は Gradle が自動で取得する。
2. リポジトリのルートで、`./gradlew :<課題番号>:test` を実行する（例: `./gradlew :b02:test`）。
3. 課題の進め方は、各課題の `README.md` に従う。最初の課題は `basic/b01-gradle/README.md`。

## 使っているもの

Kotlin 2.4.21、JDK 25、Gradle 9.8.2（Kotlin DSL、Version Catalog）、kotlin.test（JUnit Platform の上で動かす）

## ライセンス

Apache License 2.0（`LICENSE`）。書き写した手本の出典は `THIRD_PARTY_NOTICES.md` にある。
