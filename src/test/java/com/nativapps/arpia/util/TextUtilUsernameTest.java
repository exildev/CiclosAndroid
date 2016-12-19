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
public class TextUtilUsernameTest {

    private final String username;

    private final boolean expected;

    public TextUtilUsernameTest(String username, boolean expected) {
        this.username = username;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {"Abc", true}, {"123", false}, {"abc123", true}, {"abc_123", true},
            {"ac", false}, {"abcdef1234567", false}, {"1a1", true}
        });
    }

    @Test
    public void valid() {
        assertEquals(expected, TextUtil.isUsername(username));
    }

}
