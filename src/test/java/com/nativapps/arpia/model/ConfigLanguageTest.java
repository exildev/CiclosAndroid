package com.nativapps.arpia.model;

import java.io.IOException;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class ConfigLanguageTest {

    private static final Logger LOG = Logger
            .getLogger(ConfigLanguageTest.class.getName());

    private final String lang;

    private final String expected;

    public ConfigLanguageTest(String lang, String expected) {
        this.lang = lang;
        this.expected = expected;
    }

    private ConfigLanguage config;

    private static final String KEY = "test";

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {"es_CO", "prueba"}, {"en_US", "test"}, {null, "test"}
        });
    }

    @Before
    public void initialize() {
        try {
            if (lang != null) {
                config = new ConfigLanguage(lang);
            } else {
                config = new ConfigLanguage();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void test() {
        assertEquals(expected, config.getString("test"));
    }

}
