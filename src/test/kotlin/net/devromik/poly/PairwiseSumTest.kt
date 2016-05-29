package net.devromik.poly

import com.carrotsearch.hppc.IntArrayList
import com.carrotsearch.hppc.IntArrayList.from
import org.junit.Test
import org.junit.Assert.*

/**
 * @author Shulnyaev Roman
 */
class PairwiseSumTest {

    @Test fun canCalculatePairwiseSumOfTwoNonNegativeIntegerArrays() {
        var a1 = IntArrayList()
        var a2 = IntArrayList()

        assertEquals(IntArrayList(), pairwiseSumOfNonNegativeArrays(a1, a2))

        /* ****************************** */

        a1 = from(1)
        a2 = IntArrayList()

        assertEquals(IntArrayList(), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = IntArrayList()
        a2 = from(1)

        assertEquals(IntArrayList(), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = from(0)
        a2 = from(0)

        assertEquals(from(0), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = from(0)
        a2 = from(0, 1)

        assertEquals(from(0, 1), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = from(1, 0)
        a2 = from(0)

        assertEquals(from(0, 1), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = from(0, 1, 2, 3)
        a2 = from(4, 5)

        assertEquals(from(4, 5, 6, 7, 8), pairwiseSumOfNonNegativeArrays(a1, a2))

        // ****************************** //

        a1 = from(7, 0, 1, 2, 3, 1, 7, 8)
        a2 = from(4, 5, 9, 3, 3, 5, 7, 1)

        assertEquals(
            from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17),
            pairwiseSumOfNonNegativeArrays(a1, a2))
    }
}