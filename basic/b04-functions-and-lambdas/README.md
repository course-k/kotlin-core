# b04 関数とラムダ

## 位置

基礎 b04（全 20 課題の 4 番目）。前の課題: b03 制御フロー。次の課題: b05 テストを書いて走らせる。

## 終えるとできること

- 引数と戻り値の型を持つ関数を、ブロックの形と単一式の形で書ける。
- デフォルト引数と名前付き引数を使って、関数を宣言し呼び出せる。
- ラムダを変数に入れる、関数に渡す、関数から返すことができる。

## 扱う能力領域

L5（関数: 名前付き引数・デフォルト引数・単一式関数、ラムダと高階関数）、L3 の一部（return とラベル）

## やること

この課題のディレクトリは `basic/b04-functions-and-lambdas/`。コマンドはすべてリポジトリのルートで実行する。`build.gradle.kts`、テスト（`src/test/kotlin/b04/`）、確認課題の関数の枠（`src/main/kotlin/b04/Exercises.kt`）は用意してある。

### 書き写し

1. `src/main/kotlin/b04/Samples.kt` を作り、1 行目に `package b04` と書く。
2. 手本 1〜6 を、この順に `Samples.kt` に打ち込む。手本 2・4 の `fun main()` は、指定した関数名に置き換える。手本 1 の `fun main()` は打ち込まない（テストが `sum` を直接呼ぶ）。
3. `./gradlew :b04:test --tests 'b04.SamplesTest'` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。
4. 手本 1 の `sum` を、手本 1 の後に示した単一式の形に書き換え、もう一度 3 のコマンドでテストが通ることを確かめる。
5. `ANSWERS.md` の「書き写し」の問いに、自分の言葉で答える。

出典から変えたところ: 手本 2・4 は、出典の `fun main()` を `defaultArguments`・`totalTime` に変えた。手本 3・5 は、出典の `fun main()` を `lambdaFilter`・`foldSamples` に変え、`//sampleStart`・`//sampleEnd`（Web ページの表示範囲の印）を外した。手本 6 は、出典の `foo` を呼ぶ `fun main()` を省いた。

**手本 1**（関数 `sum`）

```kotlin
fun sum(x: Int, y: Int): Int {
    return x + y
}
```

- `fun 名前(引数名: 型, ...): 戻り値の型 { }` で関数を宣言する。引数は名前の後に型を書く。

手順 4 で書き換える形（単一式関数）:

```kotlin
fun sum(x: Int, y: Int) = x + y
```

- 本体が 1 つの式だけの関数は、`=` の後に式を書く。戻り値の型は式から推論される。

**手本 2**（関数 `printMessageWithPrefix` と、`main` を置き換えた `defaultArguments`）

```kotlin
fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun defaultArguments() {
    // Function called with both parameters
    printMessageWithPrefix("Hello", "Log")
    // [Log] Hello

    // Function called only with message parameter
    printMessageWithPrefix("Hello")
    // [Info] Hello

    printMessageWithPrefix(prefix = "Log", message = "Hello")
    // [Log] Hello
}
```

- `prefix: String = "Info"` は、呼び出しで省いたときに使う値（デフォルト引数）。
- `prefix = "Log"` のように引数名を書いて渡すと（名前付き引数）、順番を入れ替えられる。
- 戻り値の型を書いていない関数は、意味のある値を返さない（戻り値の型は `Unit`）。

**手本 3**（関数名 `lambdaFilter`）

```kotlin
fun lambdaFilter() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val positives = numbers.filter ({ x -> x > 0 })

    val isNegative = { x: Int -> x < 0 }
    val negatives = numbers.filter(isNegative)

    println(positives)
    // [1, 3, 5]
    println(negatives)
    // [-2, -4, -6]
}
```

- `{ 引数 -> 式 }` はラムダ（名前の無い関数）。最後の式の値を返す。
- `listOf(...)` は読み取り専用のリストを作る。`filter` は、渡したラムダが `true` を返す要素だけを集めたリストを返す。
- ラムダは、手本の `isNegative` のように変数に入れて渡すこともできる。

**手本 4**（関数 `toSeconds` と、`main` を置き換えた `totalTime`）

```kotlin
fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { value -> value * 60 * 60 }
    "minute" -> { value -> value * 60 }
    "second" -> { value -> value }
    else -> { value -> value }
}

fun totalTime() {
    val timesInMinutes = listOf(2, 10, 15, 1)
    val min2sec = toSeconds("minute")
    val totalTimeInSeconds = timesInMinutes.map(min2sec).sum()
    println("Total time is $totalTimeInSeconds secs")
    // Total time is 1680 secs
}
```

- `(Int) -> Int` は関数の型で、「`Int` を 1 つ受け取り `Int` を返す関数」を表す。
- `toSeconds` は、ラムダを返す関数。
- `map` は、各要素にラムダを適用した結果のリストを返す。`sum` は要素の合計を返す。

**手本 5**（関数名 `foldSamples`）

```kotlin
fun foldSamples() {
    // The initial value is zero.
    // The operation sums the initial value with every item in the list cumulatively.
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item })) // 6

    // Alternatively, in the form of a trailing lambda
    println(listOf(1, 2, 3).fold(0) { x, item -> x + item })  // 6
}
```

- `fold(初期値, ラムダ)` は、初期値から始めて、各要素をラムダで積み上げた結果を返す。
- 最後の引数がラムダのときは、`( )` の外に書ける（trailing lambda）。

**手本 6**（関数 `foo`）

```kotlin
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    print(" done with explicit label")
}
```

- `forEach` は、各要素についてラムダを実行する。引数が 1 つのラムダでは、引数を `it` という名前で使える。
- `lit@` はラムダに付けたラベル。`return@lit` は、`foo` からではなくラムダから戻り、次の要素に進む。

### 確認課題

用意した `src/main/kotlin/b04/Exercises.kt` の各関数の `TODO(...)` を、自分の解答に置き換える。シグネチャ（関数名・引数・戻り値の型）は変えない。

6. `fun greeting(name: String, greeting: String = "Hello"): String`: `<greeting の値>, <name の値>!` の形の文字列を返す（例: `greeting("Kotlin")` は `"Hello, Kotlin!"`）。単一式関数で書く。
7. `fun toMinutes(unit: String): (Int) -> Int`: `"hour"` なら 60 倍、`"minute"` ならそのまま、それ以外なら `0` を返す関数を返す。
8. `fun totalMinutes(times: List<Int>, unit: String): Int`: `times` の各要素を `toMinutes(unit)` で分に直して合計する。`List<Int>` は `Int` のリストの型。
9. `fun product(numbers: List<Int>): Int`: 全要素の積を、`fold` と trailing lambda で求める。空のリストには `1` を返す。
10. `fun joinSkipping(words: List<String>, skip: String): String`: `words` の要素を順につないだ文字列を返す。`skip` と等しい要素は飛ばす。`forEach` とラベル付きの `return` を使う。例: `joinSkipping(listOf("a", "b", "c"), "b")` は `"ac"`。
11. `./gradlew :b04:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。

## 合格の基準

| 対象 | 基準 | 判定 |
|---|---|---|
| 書き写し | 手本が動く（`SamplesTest` が通る）。`ANSWERS.md` の記述が模範解説と照合して要点を押さえている | テストと AI |
| 確認課題 | `./gradlew :b04:test` が通る | テスト |

AI の判定を受けるときは、この `README.md`、`ANSWERS.md`、`EXPLANATION.md` のパスを AI に渡す。`EXPLANATION.md` は、`ANSWERS.md` を書き終えてから読む。

## 出典

- Kotlin tour: Functions（手本 1〜5）: https://kotlinlang.org/docs/kotlin-tour-functions.html
- Returns and jumps（手本 6、Return to labels）: https://kotlinlang.org/docs/returns.html#return-to-labels
- Functions: https://kotlinlang.org/docs/functions.html
- Higher-order functions and lambdas: https://kotlinlang.org/docs/lambdas.html
