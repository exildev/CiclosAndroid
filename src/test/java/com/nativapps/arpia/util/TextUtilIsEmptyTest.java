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
public class TextUtilIsEmptyTest {

    private final String value;

    private final boolean expected;

    public TextUtilIsEmptyTest(String value, boolean expected) {
        this.value = value;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {null, true}, {"", true}, {" ", true}, {"a", false}, {" a ", false}
        });
    }

    @Test
    public void valid() {
        assertEquals(expected, TextUtil.isEmpty(value));
    }

}
