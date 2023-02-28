package com.example.tcmusic.core.common.util

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by TC on 28/02/2023.
 */

class StringExtensions {
    @Test
    fun `String compact to 2 letter null, one letter and more than one letter`() {
        val stringNull: String? = null
        assertEquals("", stringNull.compactTo2Letters())
        assertEquals("A", "Aaron".compactTo2Letters())
        assertEquals("BM", "Bruno Mars".compactTo2Letters())
        assertEquals("SL", "Story of my life".compactTo2Letters())
    }
}