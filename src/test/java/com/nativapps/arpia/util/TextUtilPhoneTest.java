package com.nativapps.arpia.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class TextUtilPhoneTest {

    private final String phoneNumber;

    private final boolean expected;

    public TextUtilPhoneTest(String phoneNumber, boolean expected) {
        this.phoneNumber = phoneNumber;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {"1234567", true},
            {"+01234567", true},
            {"+001234567", true},
            {"+00 123 4567", false},
            {"+0001234567", true},
            {"1234567890", true},
            {"+01234567890", true},
            {"+0(123)4567890", false},
            {"+001234567890", true},
            {"+00-123-456-7890", false},
            {"+0001234567890", true}
        });
    }

    @Test
    public void valid() {
        assertEquals(expected, TextUtil.isPhone(phoneNumber));
    }

}
