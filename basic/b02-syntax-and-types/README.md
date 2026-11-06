# b02 基本の構文と型

## 位置

基礎 b02（全 20 課題の 2 番目）。前の課題: b01 Gradle のプロジェクトを作って動かす。次の課題: b03 制御フロー。

## 終えるとできること

- `val` と `var` で変数を宣言し、型を書く場合と書かない場合を使い分けられる。
- 文字列テンプレートで、変数と式を文字列に埋め込める。
- 整数どうしの割り算と、`Int` の範囲を超える計算の結果を予測できる。

## 扱う能力領域

L1（基本の構文、パッケージ）、L2（基本の型、型変換、文字列テンプレート）

## やること

この課題のディレクトリは `basic/b02-syntax-and-types/`。コマンドはすべてリポジトリのルートで実行する。`build.gradle.kts`、テスト（`src/test/kotlin/b02/`）、出力予測のコード（`src/main/kotlin/b02/Predict.kt`）、確認課題の関数の枠（`src/main/kotlin/b02/Exercises.kt`）は用意してある。

### 書き写し

1. `src/main/kotlin/b02/Samples.kt` を作り、1 行目に `package b02` と書く。
2. 手本 1〜6 を、この順に `Samples.kt` に打ち込む。
3. `./gradlew :b02:test --tests 'b02.SamplesTest'` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。`--tests` は、走らせるテストのクラスを絞るオプション。確認課題の関数はまだ `TODO(...)` のままで、そのテストは失敗するため、ここでは手本のテスト（`SamplesTest`）だけを走らせる。
4. `ANSWERS.md` の「書き写し」の問いに、自分の言葉で答える。

書きながら見る書き方（全手本に共通）:

- `package b02` は、このファイルの宣言をパッケージ `b02` に入れる。同じパッケージの宣言どうしは、`import` なしで呼べる。テストも `package b02` に置いてある。
- 手本の `// 8` のようなコメントは、その行で出力される値を示す。打ち込んでも省いてもよい。

出典から変えたところ（手本 1〜6 に共通）: 出典では各手本が `fun main()` で、テストから呼べないため、手本ごとの関数名（`variables` など）に変えた。出典の `//sampleStart`・`//sampleEnd`（Web ページの表示範囲の印）は外した。

**手本 1**（関数名 `variables`）

```kotlin
fun variables() {
    val popcorn = 5    // There are 5 boxes of popcorn
    val hotdog = 7     // There are 7 hotdogs
    var customers = 10 // There are 10 customers in the queue

    // Some customers leave the queue
    customers = 8
    println(customers)
    // 8
}
```

- `val` は一度だけ代入できる変数、`var` は何度でも代入できる変数を宣言する。
- 型を書いていない。`5` から `Int` 型が決まる（型推論）。

**手本 2**（関数名 `stringTemplates`）

```kotlin
fun stringTemplates() {
    val customers = 10
    println("There are $customers customers")
    // There are 10 customers

    println("There are ${customers + 1} customers")
    // There are 11 customers
}
```

- 文字列の中の `$変数名` は、その変数の値に置き換わる。
- `${式}` は、式を計算した値に置き換わる。

**手本 3**（関数名 `arithmetic`）

```kotlin
fun arithmetic() {
    var customers = 10

    // Some customers leave the queue
    customers = 8

    customers = customers + 3 // Example of addition: 11
    customers += 7            // Example of addition: 18
    customers -= 3            // Example of subtraction: 15
    customers *= 2            // Example of multiplication: 30
    customers /= 3            // Example of division: 10

    println(customers) // 10
}
```

- `+=`・`-=`・`*=`・`/=` は、計算して同じ変数に代入する（複合代入）。

**手本 4**（関数名 `initialization`）

```kotlin
fun initialization() {
    // Variable declared without initialization
    val d: Int
    // Variable initialized
    d = 3

    // Variable explicitly typed and initialized
    val e: String = "hello"

    // Variables can be read because they have been initialized
    println(d) // 3
    println(e) // hello
}
```

- `val d: Int` は、型を書いて、値を後で代入する宣言。
- `val e: String = "hello"` は、型と値を両方書く宣言。

**手本 5**（関数名 `integerDivision`）

```kotlin
fun integerDivision() {
    val intValue = 5 / 2
    println(intValue) // 2

    val longValue = 5L / 2
    println(longValue) // 2
}
```

- `5L` の `L` は、`Long` 型の整数を表す。

**手本 6**（関数名 `floatingDivision`）

```kotlin
fun floatingDivision() {
    val a = 5 / 2.0
    println(a) // 2.5

    val b = 5 / 2.toDouble()
    println(b) // 2.5
}
```

- `2.0` は `Double` 型。`toDouble()` は、`Int` の値を `Double` に変換する。

### 確認課題

用意した `src/main/kotlin/b02/Exercises.kt` の 2 つの関数の `TODO(...)` を、自分の解答に置き換える。

5. `fun boxes()`: 品物 `23` 個を、1 箱に `5` 個ずつ詰める。`val` を 2 つ（品物の数、1 箱の数）使い、次の 2 行を出力する。2 行とも、数は 2 つの `val` から計算で求める。

   ```
   full boxes: 4
   average per box: 4.6
   ```

6. `fun counter()`: `var` の変数を `0` で始め、複合代入で `7` を足し、`3` を掛ける。次の 2 行を出力する。2 行目は文字列テンプレートの中の式で求める。

   ```
   count is 21
   next is 22
   ```

7. `./gradlew :b02:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。

### 出力予測

8. `src/main/kotlin/b02/Predict.kt` の `p1`〜`p3` を読み、それぞれの出力を `ANSWERS.md` に書く。
9. `./gradlew -q :b02:run --args=p1` のように、`p1`〜`p3` を実行する。
10. 予測と違った問題について、なぜ違ったかを `ANSWERS.md` に書く。
11. `p4` の 4 行がコンパイルできるかを予測し、できないならどの行かを `ANSWERS.md` に書く。
12. `Predict.kt` の説明に従ってコメントを外し、`./gradlew -q :b02:run --args=p4` で確かめる。確かめたらコメントを戻す。

## 合格の基準

| 対象 | 基準 | 判定 |
|---|---|---|
| 書き写し | 手本が動く（`SamplesTest` が通る）。`ANSWERS.md` の記述が模範解説と照合して要点を押さえている | テストと AI |
| 確認課題 | `./gradlew :b02:test` が通る | テスト |
| 出力予測 | 予測と、外れた理由の記述が正しい | AI |

AI の判定を受けるときは、この `README.md`、`ANSWERS.md`、`EXPLANATION.md` のパスを AI に渡す。`EXPLANATION.md` は、`ANSWERS.md` を書き終えてから読む。

## 出典

- Kotlin tour: Hello world（手本 1・2）: https://kotlinlang.org/docs/kotlin-tour-hello-world.html
- Kotlin tour: Basic types（手本 3・4）: https://kotlinlang.org/docs/kotlin-tour-basic-types.html
- Numbers（手本 5・6、Integer division）: https://kotlinlang.org/docs/numbers.html#integer-division
- Numbers（p2、Data overflow）: https://kotlinlang.org/docs/numbers.html#data-overflow
- Basic syntax（`package`、p3 の String templates）: https://kotlinlang.org/docs/basic-syntax.html （p3 は、出典の `val s2 = ...` と `println(s2)` を、`println(s1)` と式を直接 `println` に渡す形に変えた）
- Strings（文字列テンプレート）: https://kotlinlang.org/docs/strings.html#string-templates
