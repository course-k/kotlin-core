package b02

import kotlin.test.Test
import kotlin.test.assertEquals

class SamplesTest {
    @Test
    fun `手本 1 variables は 8 を出力する`() {
        assertEquals("8\n", captureStdout { variables() })
    }

    @Test
    fun `手本 2 stringTemplates は変数と式を埋め込んだ 2 行を出力する`() {
        assertEquals(
            "There are 10 customers\nThere are 11 customers\n",
            captureStdout { stringTemplates() },
        )
    }

    @Test
    fun `手本 3 arithmetic は複合代入の結果 10 を出力する`() {
        assertEquals("10\n", captureStdout { arithmetic() })
    }

    @Test
    fun `手本 4 initialization は 3 と hello を出力する`() {
        assertEquals("3\nhello\n", captureStdout { initialization() })
    }

    @Test
    fun `手本 5 integerDivision は整数どうしの割り算の結果 2 を 2 回出力する`() {
        assertEquals("2\n2\n", captureStdout { integerDivision() })
    }

    @Test
    fun `手本 6 floatingDivision は 2_5 を 2 回出力する`() {
        assertEquals("2.5\n2.5\n", captureStdout { floatingDivision() })
    }
}
