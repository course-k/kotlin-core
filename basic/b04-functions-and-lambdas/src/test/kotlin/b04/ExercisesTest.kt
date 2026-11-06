package b04

import kotlin.test.Test
import kotlin.test.assertEquals

class ExercisesTest {
    @Test
    fun `確認課題 6 greeting は greeting を省くと Hello であいさつする`() {
        assertEquals("Hello, Kotlin!", greeting("Kotlin"))
    }

    @Test
    fun `確認課題 6 greeting は名前付き引数で順番を入れ替えて呼べる`() {
        assertEquals("Hi, Kotlin!", greeting(greeting = "Hi", name = "Kotlin"))
    }

    @Test
    fun `確認課題 7 toMinutes は hour を 60 倍、minute をそのまま、それ以外を 0 にする関数を返す`() {
        assertEquals(120, toMinutes("hour")(2))
        assertEquals(2, toMinutes("minute")(2))
        assertEquals(0, toMinutes("day")(2))
    }

    @Test
    fun `確認課題 8 totalMinutes は全要素を分に直して合計する`() {
        assertEquals(300, totalMinutes(listOf(2, 3, 0), "hour"))
        assertEquals(45, totalMinutes(listOf(30, 15), "minute"))
        assertEquals(0, totalMinutes(listOf(), "hour"))
    }

    @Test
    fun `確認課題 9 product は全要素の積を返す`() {
        assertEquals(24, product(listOf(2, 3, 4)))
    }

    @Test
    fun `確認課題 9 product は空のリストに 1 を返す`() {
        assertEquals(1, product(listOf()))
    }

    @Test
    fun `確認課題 10 joinSkipping は skip と等しい要素だけを飛ばしてつなぐ`() {
        assertEquals("ac", joinSkipping(listOf("a", "b", "c"), "b"))
        assertEquals("acd", joinSkipping(listOf("a", "b", "c", "b", "d"), "b"))
        assertEquals("abc", joinSkipping(listOf("a", "b", "c"), "x"))
    }
}
