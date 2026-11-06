# b05 テストを書いて走らせる

## 位置

基礎 b05（全 20 課題の 5 番目）。前の課題: b04 関数とラムダ。次の課題: b06 null 安全。

## 終えるとできること

- kotlin.test でテストクラスとテスト関数を書き、Gradle で走らせられる。
- 失敗したテストの報告を読み、期待値と実際の値の違いを見つけられる。
- 仕様の境界の値を選んでテストを書ける。

## 扱う能力領域

C1（単体テスト: テストの構成、アサーション、テストの名前で仕様を表す）

## 初めて使う道具

### kotlin.test

- **何をする道具か**: Kotlin の標準のテストライブラリ。`@Test` でテスト関数を示し、`assertEquals` などのアサーション関数で結果を確かめる。JVM では JUnit の上で動く。
- **なぜこの計画で要るか**: この計画のテストは kotlin.test を主に使う。b06 以降のすべての課題で、この課題の書き方でテストを書く。
- **用意済みの設定**: `build.gradle.kts` の `testImplementation(kotlin("test"))` と `tasks.test { useJUnitPlatform() }`（b01 で書いたもの）。

### テストのコマンドと報告

| コマンド | すること |
|---|---|
| `./gradlew :b05:test` | `src/test/kotlin` のすべてのテストを走らせる |
| `./gradlew :b05:test --tests 'b05.CartTest'` | 指定したクラスのテストだけを走らせる |

- テストが失敗すると、Gradle は失敗したテストの名前と、ファイルの行番号を表示する。
- 期待値と実際の値は、報告の HTML に出る。入口の `basic/b05-testing/build/reports/tests/test/index.html` を開き、失敗したクラス、失敗したテストの順にリンクをたどる。macOS では `open basic/b05-testing/build/reports/tests/test/index.html` で開ける。

## やること

この課題のディレクトリは `basic/b05-testing/`。コマンドはすべてリポジトリのルートで実行する。`build.gradle.kts`、確認課題の関数の枠 `src/main/kotlin/b05/Shipping.kt`、確認課題の仕様のテスト `src/test/kotlin/b05/ShippingFeeSpecTest.kt` は用意してある。この課題では、テストを書くことが解答に含まれる。

### 書き写し

1. `src/main/kotlin/b05/Cart.kt` を作り、次の 2 つの関数を書く。

   ```kotlin
   package b05

   fun calculateTotal(prices: List<Int>): Int = prices.sum()

   fun isEmptyCart(prices: List<Int>): Boolean = prices.isEmpty()
   ```

   - `isEmpty()` は、リストに要素が無いとき `true` を返す。

2. `src/test/kotlin/b05/CartTest.kt` を作り、手本 1〜3 を打ち込む。3 つのテスト関数は、1 つのクラス `CartTest` の中に並べる（下の「手本 1〜3 をまとめた形」）。
3. `./gradlew :b05:test --tests 'b05.CartTest'` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。
4. 手本 1 の期待値 `35` を `36` に変えて、3 のコマンドを実行する。失敗の表示と報告の HTML を読み、期待値と実際の値を `ANSWERS.md` に写す。確かめたら `35` に戻す。
5. `ANSWERS.md` の「書き写し」の問いに、自分の言葉で答える。

**手本 1**（kotlin.test の基本の形）

```kotlin
import kotlin.test.Test
import kotlin.test.assertEquals

class CartTest {
    @Test
    fun `合計は全商品の価格の和になる`() {
        val result = calculateTotal(listOf(10, 25))
        assertEquals(35, result)
    }
}
```

- `class CartTest { }` は、テスト関数をまとめるクラス。クラスの名前は、テストする対象の名前に `Test` を付ける。
- `@Test` は、この関数がテストであることを示す注釈（アノテーション）。
- 関数名をバッククォート（`` ` ``）で囲むと、空白を含む文を関数名にできる。この計画では、テストの名前を、確かめている仕様の日本語の文で書く。
- `assertEquals(期待値, 実際の値)` は、2 つが等しくなければテストを失敗させる。引数の順番は「期待値が先」。

出典から変えたところ: 手本 1 の公式の例は、クラス `OrderService` のメソッドと `Double` の価格（`listOf(10.0, 25.0)`）を使う。クラスは b07 で扱うため、トップレベル関数と `Int` の価格に変えた。テストの名前は日本語にした。

**手本 2**（失敗時のメッセージを付ける）

```kotlin
    @Test
    fun `商品が無いときの合計は 0 になる`() {
        val result = calculateTotal(listOf())
        assertEquals(0, result, "空のカートの合計")
    }
```

- `assertEquals` の 3 つ目の引数は、失敗したときに表示するメッセージ。

手本 2・3 と `Cart.kt` は、kotlin.test の API リファレンスにある関数（`assertEquals` のメッセージ付きの形、`assertTrue`、`assertFalse`）の使い方を示すために、教材が書いたコード。

**手本 3**（真偽値を確かめる）

```kotlin
    @Test
    fun `商品が無いカートは空と判定される`() {
        assertTrue(isEmptyCart(listOf()))
        assertFalse(isEmptyCart(listOf(10)))
    }
```

- `assertTrue(式)` は式が `true` でなければ、`assertFalse(式)` は式が `false` でなければテストを失敗させる。
- 使うアサーション関数は、ファイルの先頭で `import kotlin.test.assertTrue` のように 1 つずつ import する。

**手本 1〜3 をまとめた形**（`CartTest.kt` の全体）

```kotlin
package b05

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CartTest {
    // 手本 1 の関数
    // 手本 2 の関数
    // 手本 3 の関数
}
```

### 確認課題

仕様: 送料を返す関数 `fun shippingFee(total: Int): Int`。合計 `total`（円）が 5000 以上なら送料は 0、5000 未満なら送料は 500。

6. 用意した `src/main/kotlin/b05/Shipping.kt` の `TODO(...)` を、`shippingFee` の実装に置き換える。
7. `src/test/kotlin/b05/ShippingFeeTest.kt` を作り、クラス `ShippingFeeTest` に、`shippingFee` のテストを 3 つ以上書く。テストの名前は仕様を日本語の文で書く。仕様の境目（5000 ちょうどと、その 1 つ下）を必ず含める。
8. `./gradlew :b05:test` を実行し、`BUILD SUCCESSFUL` が出ることを確かめる。用意したテスト `ShippingFeeSpecTest` も同時に走る。
9. `Shipping.kt` を、5000 ちょうどを送料 500 として扱う実装に変えて（例: `>=` を `>` に、`<` を `<=` に変える）、`./gradlew :b05:test --tests 'b05.ShippingFeeTest'` を実行する。自分のテストのうち失敗したものを `ANSWERS.md` に書く。確かめたら元に戻す。
10. 8 のコマンドで、再びすべてのテストが通ることを確かめる。

## 合格の基準

| 対象 | 基準 | 判定 |
|---|---|---|
| 書き写し | 手本が動く（`CartTest` が通る）。`ANSWERS.md` の記述が模範解説と照合して要点を押さえている | テストと AI |
| 確認課題 | `./gradlew :b05:test` が通る。`ShippingFeeTest` が 5000 と 4999 を確かめ、手順 9 で失敗するテストがある | テストと AI |

AI の判定を受けるときは、この `README.md`、`ANSWERS.md`、`EXPLANATION.md`、`src/test/kotlin/b05/ShippingFeeTest.kt` のパスを AI に渡す。`EXPLANATION.md` は、`ANSWERS.md` を書き終えてから読む。

## 出典

- Test code using Maven（手本 1、Write unit tests）: https://kotlinlang.org/docs/jvm-test-maven.html
- kotlin.test の API（`assertEquals`・`assertTrue`・`assertFalse`）: https://kotlinlang.org/api/core/kotlin-test/
- Configure a Gradle project（Set dependencies on test libraries）: https://kotlinlang.org/docs/gradle-configure-project.html#set-dependencies-on-test-libraries
- Coding conventions（テストの関数名にバッククォートを使う）: https://kotlinlang.org/docs/coding-conventions.html#names-for-test-methods
- Gradle の Test タスクの絞り込み（`--tests`）: https://docs.gradle.org/current/userguide/java_testing.html#test_filtering
