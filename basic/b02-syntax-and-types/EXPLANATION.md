# b02 模範解説

`ANSWERS.md` を書き終えてから読む。

## なぜこう書くか

### 書き写し

- **`val` を基本にする（問い 1）**: `val` で宣言した変数に再び代入すると、コンパイルエラー（`'val' cannot be reassigned.`）になる。値が変わらない変数は `val` にしておくと、読む人は「この名前はずっと同じ値」と分かり、誤った再代入はコンパイラが止める。Kotlin 公式のコーディング規約も、変更しない変数には `val` を使うよう勧めている。`var` は、手本 1・3 の `customers` のように値を変える必要があるときだけ使う。
- **型推論（問い 2）**: 初期値 `10` から `Int` 型が決まる。型を書かなくても型は決まっており、後から `customers = "ten"` と書くとコンパイルエラーになる。整数の値は、`Int` の範囲に収まれば `Int`、収まらなければ `Long` と推論される。
- **後から代入する `val`（問い 3）**: `val` の制約は「代入は 1 回だけ」で、宣言と同時である必要はない。コンパイラは、読む前に必ず 1 回だけ代入されることを確かめる。2 回目の `d = 4` はコンパイルエラーになる。代入前に読むと `Variable 'd' must be initialized` のエラーになる。
- **整数どうしの割り算（問い 4）**: 両方が整数型の割り算は、整数の結果を返し、小数部分を捨てる。`5 / 2.0` は、片方が `Double` なので `Double` の割り算になる。`5 / 2.toDouble()` は、`2` を `Double` に変換してから割る。Kotlin は数値の型を自動で変換しないため、変換は `toDouble()` のように明示する。

### 確認課題の解答例

```kotlin
package b02

fun boxes() {
    val items = 23
    val perBox = 5
    println("full boxes: ${items / perBox}")
    println("average per box: ${items / perBox.toDouble()}")
}

fun counter() {
    var count = 0
    count += 7
    count *= 3
    println("count is $count")
    println("next is ${count + 1}")
}
```

- 1 行目は整数どうしの割り算で、小数部分を捨てて `4` になる。2 行目は片方を `Double` に変換して `4.6` を得る。
- テンプレートの中で計算するときは `${ }` で囲む。`$count + 1` と書くと `21 + 1` という文字列になる。

### 出力予測

| 問題 | 出力 | 理由 |
|---|---|---|
| p1 | `3` / `3.5` / `-3` | 整数どうしの割り算は小数部分を捨てる。捨てる向きは 0 に近い側で、`-7 / 2` は `-4` ではなく `-3` になる |
| p2 | `-294967296` / `6000000000` | `n` は `Int` と推論される。`n + n` は `Int` の最大値 2,147,483,647 を超え、オーバーフローして負の値になる。コンパイラもエラーにしない。`m` は `Int` に収まらないため `Long` と推論され、`m + m` は正しく計算される |
| p3 | `a is 1` / `a was 1, but now is 2` | 文字列テンプレートは、その式を評価した時点の値で文字列を作る。後で `a` を変えても、作った `s1` は変わらない |

p4: 4 行目 `total = 200` がコンパイルエラー（`'val' cannot be reassigned.`）。`count` は `var` なので 3 行目は通る。

## よくある誤り

- `average` を `items / perBox` で求め、`4` になる。小数が要る計算では、片方を `Double` にする。
- 大きな数を `Int` の変数で計算し、オーバーフローに気づかない。オーバーフローは実行時の例外にもならず、誤った値のまま進む。範囲を超えうる値は `Long` にする。
- `val` で宣言したリストなどの中身も変えられないと考える。`val` が禁じるのは変数への再代入で、指している中身の変更ではない（b09 で扱う）。

## Java ではこうだが Kotlin では／TypeScript ではこうだが Kotlin では

- **Java**: 変数は型を先に書く（`int count = 0;`）。Kotlin は `val`／`var` を先に書き、型は推論に任せるか、名前の後に `: Int` と書く。Java の `final` 変数が Kotlin の `val` に当たる。
- **Java**: `int` どうしの割り算、オーバーフロー、0 に近い側への切り捨ては Kotlin と同じ。Kotlin では `Int` から `Long` や `Double` への暗黙の変換（拡大変換）が無く、`toLong()` や `toDouble()` で明示する。
- **TypeScript**: 数値は `number` 型の 1 種類（浮動小数点数）で、`7 / 2` は `3.5` になる。Kotlin では `7 / 2` は `3`。整数と浮動小数点数は別の型。
- **TypeScript**: `const` が `val`、`let` が `var` に当たる。文字列テンプレートは TypeScript ではバッククォートの中の `${式}`、Kotlin では普通のダブルクォートの中の `$変数` と `${式}`。

## 出典

- Numbers（Integer types、Integer division、Data overflow）: https://kotlinlang.org/docs/numbers.html
- `Int.div`（0 に近い側への切り捨て）: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/div.html
- Coding conventions（Immutability）: https://kotlinlang.org/docs/coding-conventions.html#immutability
