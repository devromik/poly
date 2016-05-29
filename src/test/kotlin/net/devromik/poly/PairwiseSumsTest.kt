package net.devromik.poly

import org.junit.Test
import org.junit.Assert.*

/**
 * @author Shulnyaev Roman
 */
class PairwiseSumsTest {

    @Test fun canCalculatePairwiseSumsOfTwoPositiveIntegerArrays() {
        var a = intArrayOf()
        var b = intArrayOf()

        assertArrayEquals(intArrayOf(), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(1)
        b = intArrayOf()

        assertArrayEquals(intArrayOf(), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf()
        b = intArrayOf(1)

        assertArrayEquals(intArrayOf(), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(0)
        b = intArrayOf(0)

        assertArrayEquals(intArrayOf(0), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(0)
        b = intArrayOf(0, 1)

        assertArrayEquals(intArrayOf(0, 1), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(1, 0)
        b = intArrayOf(0)

        assertArrayEquals(intArrayOf(0, 1), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(0, 1, 2, 3)
        b = intArrayOf(4, 5)

        assertArrayEquals(intArrayOf(4, 5, 6, 7, 8), pairwiseSumsOfNonNegativeArrays(a, b))

        // ****************************** //

        a = intArrayOf(7, 0, 1, 2, 3, 1, 7, 8)
        b = intArrayOf(4, 5, 9, 3, 3, 5, 7, 1)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17),
            pairwiseSumsOfNonNegativeArrays(a, b))
    }
}