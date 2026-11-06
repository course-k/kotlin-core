# b03 制御フロー

## 位置

基礎 b03（全 20 課題の 3 番目）。前の課題: b02 基本の構文と型。次の課題: b04 関数とラムダ。

## 終えるとできること

- `if` と `when` を、値を返す式として書ける。
- 範囲（`..`）を使った `for` ループと、`while`・`do-while` ループを書ける。
- 範囲の端と、`when` がどの枝を選ぶかを予測できる。

## 扱う能力領域

L3（制御フロー: `if`・`when`・ループ・式としての制御）

## やること

この課題のディレクトリは `basic/b03-control-flow/`。コマンドはすべてリポジトリのルートで実行する。`build.gradle.kts`、テスト（`src/test/kotlin/b03/`）、出力予測のコード（`src/main/kotlin/b03/Predict.kt`）、確認課題の関数の枠（`src/main/kotlin/b03/Exercises.kt`）は用意してある。

### 書き写し

1. `src/main/kotlin/b03/Samples.kt` を作り、1 行目に `package b03` と書く。
2. 手本 1〜6 を、この順に `Samples.kt` に打ち込む。

出典から変えたところ（手本 1〜6 に共通）: 出典では各手本が `fun main()` で、テストから呼べないため、手本ごとの関数名（`ifStatement` など）に変えた。出典の `//sampleStart`・`//sampleEnd`（Web ページの表示範囲の印）は外した。
3. `./gradlew :b03:test --tests 'b03.SamplesTest'` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。
4. `ANSWERS.md` の「書き写し」の問いに、自分の言葉で答える。

**手本 1**（関数名 `ifStatement`）

```kotlin
fun ifStatement() {
    val d: Int
    val check = true

    if (check) {
        d = 1
    } else {
        d = 2
    }

    println(d)
    // 1
}
```

- `if (条件) { } else { }` は、条件で実行する枝を選ぶ。

**手本 2**（関数名 `ifExpression`）

```kotlin
fun ifExpression() {
    val a = 1
    val b = 2

    println(if (a > b) a else b) // Returns a value: 2
}
```

- `if (条件) 値1 else 値2` は、選んだ枝の値を返す式として書ける。値を返す `if` には `else` が要る。

**手本 3**（関数名 `whenExpression`）

```kotlin
fun whenExpression() {
    val obj = "Hello"

    val result = when (obj) {
        // If obj equals "1", sets result to "one"
        "1" -> "One"
        // If obj equals "Hello", sets result to "Greeting"
        "Hello" -> "Greeting"
        // Sets result to "Unknown" if no previous condition is satisfied
        else -> "Unknown"
    }
    println(result)
    // Greeting
}
```

- `when (対象) { 値 -> 結果 }` は、対象と等しい値の枝を選び、その結果を返す。
- `else` は、どの枝にも当てはまらないときの枝。

**手本 4**（関数名 `whenWithoutSubject`）

```kotlin
fun whenWithoutSubject() {
    val trafficLightState = "Red" // This can be "Green", "Yellow", or "Red"

    val trafficAction = when {
        trafficLightState == "Green" -> "Go"
        trafficLightState == "Yellow" -> "Slow down"
        trafficLightState == "Red" -> "Stop"
        else -> "Malfunction"
    }

    println(trafficAction)
    // Stop
}
```

- `when` の後に対象を書かない形では、各枝の左に真偽値の条件を書く。

**手本 5**（関数名 `forRange`）

```kotlin
fun forRange() {
    for (number in 1..5) {
        // number is the iterator and 1..5 is the range
        print(number)
    }
    // 12345
}
```

- `1..5` は、1 から 5 までの範囲。`for (変数 in 範囲)` で、範囲の値を順に取り出す。
- `print` は、改行せずに出力する。

**手本 6**（関数名 `whileLoops`）

```kotlin
fun whileLoops() {
    var cakesEaten = 0
    var cakesBaked = 0
    while (cakesEaten < 3) {
        println("Eat a cake")
        cakesEaten++
    }
    do {
        println("Bake a cake")
        cakesBaked++
    } while (cakesBaked < cakesEaten)
    // Eat a cake
    // Eat a cake
    // Eat a cake
    // Bake a cake
    // Bake a cake
    // Bake a cake
}
```

- `while (条件) { }` は、条件を確かめてから本体を実行する。`do { } while (条件)` は、本体を実行してから条件を確かめる。
- `++` は、変数に 1 を足す。

### 確認課題

用意した `src/main/kotlin/b03/Exercises.kt` の 3 つの関数の `TODO(...)` を、自分の解答に置き換える。

5. `fun grade()`: `val score = 72` とし、対象を書かない `when` で評価を求めて出力する。80 以上は `A`、60 以上は `B`、それ以外は `C`。出力は `B`。
6. `fun stairs()`: `1..4` の `for` ループで、値を 1 行ずつ出力しながら `var` の変数に足していき、最後に `total: 10` を出力する。
7. `fun doubling()`: `var` の変数を `1` で始め、`100` 未満の間、`while` ループで 2 倍にし、回数を数える。最後に `7 steps, value 128` を出力する（回数と値は計算で求める）。
8. `./gradlew :b03:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。

### 出力予測

9. `src/main/kotlin/b03/Predict.kt` の `p1`〜`p3` を読み、それぞれの出力を `ANSWERS.md` に書く。`p2` の `..<` と `downTo` は初めて見る書き方なので、記号と名前から意味を推測して予測する。
10. `./gradlew -q :b03:run --args=p1` のように、`p1`〜`p3` を実行する。
11. 予測と違った問題について、なぜ違ったかを `ANSWERS.md` に書く。

### 確認課題（範囲の書き方）

p2 で見た書き方を、自分の手で書く。

- `1..<4` は、1 から 4 の手前まで（1, 2, 3）の範囲。終わりの値を含まない。
- `4 downTo 1` は、4 から 1 まで減る向き（4, 3, 2, 1）の範囲。

12. 用意した `Exercises.kt` の `fun ranges()` の `TODO(...)` を置き換える。`..<` を使った `for` ループで `0` から `4` の手前までを改行なしで出力して改行し、`downTo` を使った `for` ループで `5` から `1` までを改行なしで出力して改行する。出力は `0123` と `54321` の 2 行。
13. `./gradlew :b03:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。

## 合格の基準

| 対象 | 基準 | 判定 |
|---|---|---|
| 書き写し | 手本が動く（`SamplesTest` が通る）。`ANSWERS.md` の記述が模範解説と照合して要点を押さえている | テストと AI |
| 確認課題 | `./gradlew :b03:test` が通る | テスト |
| 出力予測 | 予測と、外れた理由の記述が正しい | AI |

AI の判定を受けるときは、この `README.md`、`ANSWERS.md`、`EXPLANATION.md` のパスを AI に渡す。`EXPLANATION.md` は、`ANSWERS.md` を書き終えてから読む。

## 出典

- Kotlin tour: Control flow（手本 1〜6）: https://kotlinlang.org/docs/kotlin-tour-control-flow.html
- Conditions and loops（`if` と `when` の式、ブロックの値）: https://kotlinlang.org/docs/control-flow.html
- Ranges and progressions（`..<`、`downTo`）: https://kotlinlang.org/docs/ranges.html
