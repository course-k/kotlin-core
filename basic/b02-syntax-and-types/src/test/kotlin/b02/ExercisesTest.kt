package b02

import kotlin.test.Test
import kotlin.test.assertEquals

class ExercisesTest {
    @Test
    fun `確認課題 5 boxes は満杯の箱の数と 1 箱あたりの平均を出力する`() {
        assertEquals(
            "full boxes: 4\naverage per box: 4.6\n",
            captureStdout { boxes() },
        )
    }

    @Test
    fun `確認課題 6 counter は計算の途中と最後の値を出力する`() {
        assertEquals(
            "count is 21\nnext is 22\n",
            captureStdout { counter() },
        )
    }
}
