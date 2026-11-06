package b05

import kotlin.test.Test
import kotlin.test.assertEquals

// 教材が用意した、確認課題の仕様のテスト。学習者が書くテストは ShippingFeeTest.kt に置く
class ShippingFeeSpecTest {
    @Test
    fun `合計が 5000 円以上なら送料は 0 円`() {
        assertEquals(0, shippingFee(5000))
        assertEquals(0, shippingFee(12000))
    }

    @Test
    fun `合計が 5000 円未満なら送料は 500 円`() {
        assertEquals(500, shippingFee(4999))
        assertEquals(500, shippingFee(0))
    }
}
