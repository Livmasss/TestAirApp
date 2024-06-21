package com.livmas.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyDecimalFormatterTest {
    @Test
    fun formatPrice_fewDigitsTest() {
        val formatted = MyDecimalFormatter.formatPrice(2000000).replace(' ', ' ')
        assertEquals("2 000 000", formatted)
    }
    @Test
    fun formatPrice_lotDigitsTest() {
        val formatted = MyDecimalFormatter.formatPrice(2001900000).replace(' ', ' ')
        assertEquals("2 001 900 000", formatted)
    }
}