# b03 模範解説

`ANSWERS.md` を書き終えてから読む。

## なぜこう書くか

### 書き写し

- **枝ごとの代入（問い 1）**: `val` の制約は「代入はちょうど 1 回」。`if` と `else` の枝はどちらか一方しか実行されないため、どの道筋でも代入は 1 回になる。コンパイラはこれを道筋ごとに確かめる。
- **値を返す `if`（問い 2）**: `val d = if (check) 1 else 2` と 1 行で書ける。宣言と代入が 1 か所にまとまり、「代入を忘れた枝」が起きない。値を選ぶだけの `if` は、式として書く方がよい。
- **`else` が要る理由（問い 3）**: 値を返す `when` は、どの入力でも何かの値を返す必要がある。対象が `String` のように値の種類が無数にある型では、すべての値を枝に書けないため、`else` が無いとコンパイルエラー（`'when' expression must be exhaustive`）になる。対象が `Boolean`・enum・sealed のときは、全部の値を枝に書けば `else` は要らない（b13 で扱う）。手本 4 のように対象を書かない `when` では、各枝の条件が任意の真偽式で、どれも真にならない場合があるため、同じく `else` が要る。
- **`while` と `do-while`（問い 4）**: `while` は先に条件を確かめるため 0 回。`do-while` は先に本体を実行するため、`cakesBaked` が `1` になって条件 `1 < 3` を満たし、`3` になるまで 3 回実行する。`do-while` の本体は、条件にかかわらず最低 1 回実行される。

### 確認課題の解答例

```kotlin
package b03

fun grade() {
    val score = 72
    val result = when {
        score >= 80 -> "A"
        score >= 60 -> "B"
        else -> "C"
    }
    println(result)
}

fun stairs() {
    var total = 0
    for (step in 1..4) {
        println(step)
        total += step
    }
    println("total: $total")
}

fun doubling() {
    var value = 1
    var steps = 0
    while (value < 100) {
        value *= 2
        steps++
    }
    println("$steps steps, value $value")
}

fun ranges() {
    for (i in 0..<4) print(i)
    println()
    for (i in 5 downTo 1) print(i)
    println()
}
```

- `grade` の枝は、上から順に確かめる。`score >= 60` の枝は、`score >= 80` が偽だったときだけ確かめられるため、`80 > score >= 60` と書く必要が無い。

### 出力予測

| 問題 | 出力 | 理由 |
|---|---|---|
| p1 | `checking` / `large` | `if` の枝はブロックにでき、ブロックの最後の式の値が `if` の値になる。`println("checking")` はブロックを実行したときに出力される |
| p2 | `123` / `12` / （空行） / `321` | `..` は終わりの値を含み、`..<` は含まない。`3..1` は「3 から 1 へ増える範囲」で、要素が無いため 1 回も回らない。減る向きに回すときは `downTo` を使う |
| p3 | `over 2` | `when` は値を返す式で、上から順に条件を確かめ、最初に真になった 1 つの枝の値を返す。`n > 3` も真だが、確かめられない |

## よくある誤り

- 減る向きのループを `for (i in 10..1)` と書く。エラーにならず、1 回も回らない。`10 downTo 1` と書く。
- 範囲の終わりを含まないつもりで `0..size` と書き、1 つ多く回る。終わりを含まないときは `0..<size` と書く。
- `when` で条件の広い枝を上に書き、下の枝に届かない。p3 の 2 つの枝を入れ替えると、`n = 4` で `over 3` になる。

## Java ではこうだが Kotlin では／TypeScript ではこうだが Kotlin では

- **Java**: `if` は文で、値を返さない。値を選ぶときは三項演算子 `a > b ? a : b` を使う。Kotlin には三項演算子が無く、`if` が式として値を返す。
- **Java**: `switch` 文は、`break` を書かないと次の `case` に進む（フォールスルー）。Kotlin の `when` は、選んだ 1 つの枝だけを実行し、`break` を書かない。Java 14 以降の `switch` 式（`->` の形）は `when` に近い。
- **Java**: `for (int i = 0; i < n; i++)` の形は Kotlin に無い。`for (i in 0..<n)` と範囲で書く。
- **TypeScript**: `if` は文で、値を返さない。値を選ぶときは三項演算子を使う。`switch` は Java と同じくフォールスルーする。
- **TypeScript**: `for (const x of items)` が Kotlin の `for (x in items)` に当たる。TypeScript の `for (const k in obj)` はキーを列挙するが、Kotlin の `in` は値を取り出す。

## 出典

- Conditions and loops: https://kotlinlang.org/docs/control-flow.html
- Ranges and progressions: https://kotlinlang.org/docs/ranges.html
