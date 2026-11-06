package b04

import kotlin.test.Test
import kotlin.test.assertEquals

class SamplesTest {
    @Test
    fun `手本 1 sum は 2 つの整数の和を返す`() {
        assertEquals(3, sum(1, 2))
        assertEquals(6, sum(-4, 10))
    }

    @Test
    fun `手本 2 printMessageWithPrefix は prefix を省くと Info を使う`() {
        assertEquals(
            "[Log] Hello\n[Info] Hello\n[Log] Hello\n",
            captureStdout { defaultArguments() },
        )
    }

    @Test
    fun `手本 3 lambdaFilter は正の数と負の数に分けて出力する`() {
        assertEquals("[1, 3, 5]\n[-2, -4, -6]\n", captureStdout { lambdaFilter() })
    }

    @Test
    fun `手本 4 toSeconds は単位に応じた変換の関数を返す`() {
        assertEquals(7200, toSeconds("hour")(2))
        assertEquals(120, toSeconds("minute")(2))
        assertEquals("Total time is 1680 secs\n", captureStdout { totalTime() })
    }

    @Test
    fun `手本 5 foldSamples は 2 つの書き方で同じ合計 6 を出力する`() {
        assertEquals("6\n6\n", captureStdout { foldSamples() })
    }

    @Test
    fun `手本 6 foo はラベル付きの return で 3 だけを飛ばす`() {
        assertEquals("1245 done with explicit label", captureStdout { foo() })
    }
}
