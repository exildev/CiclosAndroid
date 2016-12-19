package com.nativapps.arpia.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class TextUtilEmailTest {

    private final String email;

    private final boolean expected;

    public TextUtilEmailTest(String email, boolean expected) {
        this.email = email;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {"example@hostname.com", true},
            {"example2@arpia.com.co", true},
            {"example@2.10", false},
            {"example@2.co.x", false}
        });
    }

    @Test
    public void valid() {
        assertEquals(expected, TextUtil.isEmail(email));
    }

}
