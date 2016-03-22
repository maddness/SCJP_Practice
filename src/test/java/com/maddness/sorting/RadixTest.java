package com.maddness.sorting;

import org.junit.Test;

import static com.maddness.sorting.Radix.getDigit;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RadixTest {

    private Radix sort = new Radix();

    @Test
    public void testIDigitGetting() throws Exception {
        assertThat(getDigit(234, 2), is(3));
        assertThat(getDigit(9003, 1), is(3));
        assertThat(getDigit(7, 3), is(0));
        assertThat(getDigit(800, 3), is(8));
        assertThat(getDigit(0, 1), is(0));
    }
}