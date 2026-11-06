package b03

import kotlin.test.Test
import kotlin.test.assertEquals

class ExercisesTest {
    @Test
    fun `確認課題 5 grade は 72 点を B と出力する`() {
        assertEquals("B\n", captureStdout { grade() })
    }

    @Test
    fun `確認課題 6 stairs は 1 から 4 を 1 行ずつ出力し、最後に合計を出力する`() {
        assertEquals("1\n2\n3\n4\ntotal: 10\n", captureStdout { stairs() })
    }

    @Test
    fun `確認課題 7 doubling は 100 以上になるまで倍にした回数と値を出力する`() {
        assertEquals("7 steps, value 128\n", captureStdout { doubling() })
    }

    @Test
    fun `確認課題 12 ranges は終わりを含まない範囲と減る向きの範囲を出力する`() {
        assertEquals("0123\n54321\n", captureStdout { ranges() })
    }
}
