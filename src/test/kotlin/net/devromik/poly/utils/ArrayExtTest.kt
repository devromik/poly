package net.devromik.poly.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class ArrayExtTest {

    @Test fun arrayOfDoubleCanBeMultipliedByDouble() {
        assertArrayEquals(arrayOf(2.0, 4.0, 6.0), arrayOf(1.0, 2.0, 3.0) * 2.0)
    }
}