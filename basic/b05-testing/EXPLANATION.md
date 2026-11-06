# b05 模範解説

`ANSWERS.md` を書き終えてから読む。

## なぜこう書くか

### 書き写し

- **失敗の報告（問い 1）**: Gradle は `CartTest > 合計は全商品の価格の和になる() FAILED` と、`AssertionFailedError at CartTest.kt:<行番号>` を表示する。報告の HTML には `expected: <36> but was: <35>` と出る。テストの名前・ファイルの行・期待値と実際の値の 3 つで、何が壊れたかを特定する。
- **期待値が先（問い 2）**: `assertEquals` は、1 つ目の引数を `expected`、2 つ目を `actual` として報告する。逆に書くと、報告が「期待は 35、実際は 36」と逆になり、テストと実装のどちらが誤っているかを読み違える。
- **仕様の文の名前（問い 3）**: 失敗したとき、テストの名前だけで「どの仕様が破れたか」が分かる。テストの一覧が、そのまま仕様の一覧として読める。この計画では、テストの名前を、確かめている仕様の日本語の文で書く。
- **`import` を 1 つずつ書く**: kotlin.test のアサーション関数はパッケージ `kotlin.test` のトップレベル関数で、使うものを import する。

### 確認課題の解答例

```kotlin
package b05

fun shippingFee(total: Int): Int = if (total >= 5000) 0 else 500
```

```kotlin
package b05

import kotlin.test.Test
import kotlin.test.assertEquals

class ShippingFeeTest {
    @Test
    fun `5000 円ちょうどは送料無料`() {
        assertEquals(0, shippingFee(5000))
    }

    @Test
    fun `4999 円は送料 500 円`() {
        assertEquals(500, shippingFee(4999))
    }

    @Test
    fun `5000 円を大きく超えても送料無料`() {
        assertEquals(0, shippingFee(30000))
    }
}
```

- **境界の値（確認課題の問い 2）**: 「以上」「未満」の誤り（`>=` と `>`）は、境目の値でしか結果が変わらない。`>` に変えた実装でも、`100` と `10000` は正しい送料を返し、テストは通る。5000（境目ちょうど）と 4999（境目の 1 つ下）を確かめると、`5000 円ちょうどは送料無料` が失敗して誤りに気づく。
- 手順 9 のように、わざと実装を壊してテストが失敗するかを確かめると、テストが仕様を守れているかを確かめられる。

## よくある誤り

- 期待値と実際の値の引数を逆に書き、失敗の報告を読み違える。
- 境目から離れた値だけをテストし、「以上」と「より大きい」の取り違えを見逃す。
- 1 つのテスト関数に無関係な確認を詰め込み、失敗したときにどの仕様が破れたか分からなくなる。1 つのテスト関数では、1 つの仕様を確かめる。

## ツールが肩代わりしていること

- **Gradle の `test` タスク**: テストのクラスを探して実行し、結果を集め、失敗があればビルドを失敗にする。報告の HTML と XML（`build/test-results/`）も作る。
- **JUnit Platform**: `@Test` の付いた関数を見つけて呼ぶ。kotlin.test の `@Test` は、`useJUnitPlatform()` の設定では JUnit Jupiter の `@Test`（`org.junit.jupiter.api.Test`）の別名になっている。

## Java ではこうだが Kotlin では／TypeScript ではこうだが Kotlin では

- **Java**: JUnit では `org.junit.jupiter.api.Assertions.assertEquals` を使い、テストの名前を文にするときは `@DisplayName("...")` を付ける。Kotlin では関数名をバッククォートで囲んで文にでき、アサーションは kotlin.test のトップレベル関数を import する。
- **TypeScript**: Jest や Vitest の `test("仕様の文", () => { expect(actual).toBe(expected) })` が、Kotlin の `` @Test fun `仕様の文`() { assertEquals(expected, actual) } `` に当たる。`expect` は実際の値を先に書くが、`assertEquals` は期待値を先に書く。

## 出典

- Test code using Maven（Write unit tests）: https://kotlinlang.org/docs/jvm-test-maven.html
- kotlin.test の API: https://kotlinlang.org/api/core/kotlin-test/
- Coding conventions（Names for test methods）: https://kotlinlang.org/docs/coding-conventions.html#names-for-test-methods
- Test code using JUnit in JVM（JUnit の `Assertions` を使う書き方）: https://kotlinlang.org/docs/jvm-test-using-junit.html
