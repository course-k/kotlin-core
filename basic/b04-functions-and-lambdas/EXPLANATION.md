# b04 模範解説

`ANSWERS.md` を書き終えてから読む。

## なぜこう書くか

### 書き写し

- **単一式関数（問い 1）**: 本体が式 1 つなら、その式の型が戻り値の型になるため、コンパイラが推論できる。ブロックの形（`{ return ... }`）では、戻り値の型を書く必要がある（書かないと `Unit` になる）。公開して他から呼ばれる関数は、型を書いておくと、本体を変えたときに戻り値の型が意図せず変わることを防げる。
- **デフォルト引数と名前付き引数（問い 2）**: Java では、引数の少ない版のメソッドを別に書く（オーバーロード）。Kotlin では、宣言 1 つで省略できる引数を表せる。名前付き引数は、同じ型の引数が並ぶ関数で、取り違えを防ぎ、呼び出し側を読みやすくする。
- **関数の型（問い 3）**: `(Int) -> Int` は「`Int` を 1 つ受け取り `Int` を返す関数」の型。`toSeconds("minute")` は関数を返すので、その場で `(2)` を付けて呼べる。値は `120`。
- **ラベル付きの return（問い 4）**: ラムダの中のラベル無しの `return` は、ラムダを囲む関数 `foo` から戻る。出力は `12` になり、` done with explicit label` も出ない。`return@lit` は、ラムダの 1 回の実行から戻るだけで、`forEach` は次の要素に進む。ラムダの中でラベル無しの `return` を書けるのは、`forEach` のようなインライン関数に渡したラムダだけ。
- **trailing lambda（手本 5）**: 最後の引数がラムダのときは、括弧の外に書くのが Kotlin の慣習。`forEach { }` や `map { }` も、この書き方。

### 確認課題の解答例

```kotlin
package b04

fun greeting(name: String, greeting: String = "Hello"): String = "$greeting, $name!"

fun toMinutes(unit: String): (Int) -> Int = when (unit) {
    "hour" -> { value -> value * 60 }
    "minute" -> { value -> value }
    else -> { value -> 0 }
}

fun totalMinutes(times: List<Int>, unit: String): Int = times.map(toMinutes(unit)).sum()

fun product(numbers: List<Int>): Int = numbers.fold(1) { acc, n -> acc * n }

fun joinSkipping(words: List<String>, skip: String): String {
    var result = ""
    words.forEach {
        if (it == skip) return@forEach
        result = "$result$it"
    }
    return result
}
```

- `joinSkipping` の `return@forEach` は、ラムダを渡した関数の名前を暗黙のラベルとして使う書き方（手本 6 の `lit@` を書かない形）。
- `product` の初期値を `0` にすると、どんなリストでも `0` になる。積の初期値は `1`。
- `toMinutes` の `else` の枝で使わない引数 `value` は、`_` と書くと「使わない」ことを読む人に示せる（`{ _ -> 0 }`）。

## よくある誤り

- ラムダの中で、ループの `continue` のつもりでラベル無しの `return` を書き、関数ごと終わる。
- ブロックの形の関数で戻り値の型を書き忘れ、`return x + y` が `Return type mismatch: expected 'Unit', actual 'Int'.` のエラーになる。
- ラムダの最後に `return` を書く。ラムダは最後の式の値を返し、ラベル無しの `return` は囲む関数から戻る。

## Java ではこうだが Kotlin では／TypeScript ではこうだが Kotlin では

- **Java**: 省略できる引数はオーバーロードで表す。Kotlin はデフォルト引数で表す。Java から Kotlin のデフォルト引数付き関数を呼ぶときは、`@JvmOverloads` を付けないとオーバーロードが作られない（L17 で扱う）。
- **Java**: ラムダは関数型インターフェース（`Function<Integer, Integer>` など）として扱う。Kotlin は `(Int) -> Int` という関数の型を持つ。Java のラムダの中の `return` はラムダから戻るが、Kotlin のインライン関数に渡したラムダの中のラベル無しの `return` は、囲む関数から戻る。
- **TypeScript**: デフォルト引数（`prefix = "Info"`）は同じ考え方。名前付き引数は無く、オブジェクトを 1 つ渡して代わりにする。
- **TypeScript**: アロー関数 `(x) => x > 0` が Kotlin のラムダ `{ x -> x > 0 }` に当たる。関数の型の書き方は `(x: number) => number` と `(Int) -> Int` で似ている。`forEach` のコールバックの `return` はコールバックから戻る（Kotlin の `return@forEach` に当たる）。

## 出典

- Functions: https://kotlinlang.org/docs/functions.html
- Higher-order functions and lambdas（trailing lambda、`it`、`_`）: https://kotlinlang.org/docs/lambdas.html
- Returns and jumps: https://kotlinlang.org/docs/returns.html
- Inline functions（Non-local jump expressions）: https://kotlinlang.org/docs/inline-functions.html
- Calling Kotlin from Java（`@JvmOverloads`）: https://kotlinlang.org/docs/java-to-kotlin-interop.html
