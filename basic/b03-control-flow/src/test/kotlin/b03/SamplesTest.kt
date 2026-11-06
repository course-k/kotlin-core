package b03

import kotlin.test.Test
import kotlin.test.assertEquals

class SamplesTest {
    @Test
    fun `手本 1 ifStatement は条件で選んだ値 1 を出力する`() {
        assertEquals("1\n", captureStdout { ifStatement() })
    }

    @Test
    fun `手本 2 ifExpression は if が返した大きい方の値 2 を出力する`() {
        assertEquals("2\n", captureStdout { ifExpression() })
    }

    @Test
    fun `手本 3 whenExpression は一致した枝の値 Greeting を出力する`() {
        assertEquals("Greeting\n", captureStdout { whenExpression() })
    }

    @Test
    fun `手本 4 whenWithoutSubject は条件が真になった枝の値 Stop を出力する`() {
        assertEquals("Stop\n", captureStdout { whenWithoutSubject() })
    }

    @Test
    fun `手本 5 forRange は 1 から 5 までを改行なしで出力する`() {
        assertEquals("12345", captureStdout { forRange() })
    }

    @Test
    fun `手本 6 whileLoops は食べた数だけ焼く`() {
        assertEquals(
            "Eat a cake\n".repeat(3) + "Bake a cake\n".repeat(3),
            captureStdout { whileLoops() },
        )
    }
}
