package com.nativapps.arpia.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class ConfigLanguage {

    /**
     * Properties file for language selected.
     */
    private final Properties propLanguage;

    /**
     * Format of path file for properties language.
     */
    private static final String FORMAT_PATH_FILE = "com/nativapps/arpia/"
            + "language/language_%s.properties";

    /**
     * Default language.
     */
    public static final String DEFAULT_LANGUAGE = "en_US";

    /**
     * Create a new instance of <code>ConfigLanguage</code> with english
     * language.
     *
     * @throws IOException if the default properties not exists.
     */
    public ConfigLanguage() throws IOException {
        this.propLanguage = new Properties();
        initialize(DEFAULT_LANGUAGE);
    }

    /**
     * Create a new instance of <code>ConfigLanguage</code> with the language
     * selected.
     * <p>
     * The accepted language format in the {@code lang} parameter is as follows:
     * <ul>
     * <li>%LANG_CODE%_%COUNTRY_CODE%</li>
     * </ul>
     * <p>
     * being LANG_CODE the language code hosted by
     * <a href="https://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1</a> and
     * COUNTRY_CODE the country code hosted by
     * <a href="https://es.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a>.
     *
     * @param lang specific language.
     * @throws IOException if the default properties not exists.
     */
    public ConfigLanguage(String lang) throws IOException {
        this.propLanguage = new Properties();
        if (lang == null || lang.trim().length() == 0)
            initialize(DEFAULT_LANGUAGE);
        else
            initialize(lang);
    }

    /**
     * Create a new instance of <code>ConfigLanguage</code> with the language
     * selected from <code>Locale</code> class.
     *
     * @param locale Locale language.
     * @throws IOException if the default properties not exists.
     */
    public ConfigLanguage(Locale locale) throws IOException {
        this.propLanguage = new Properties();
        if (locale == null)
            initialize(DEFAULT_LANGUAGE);
        else
            initialize(locale.toString());
    }

    /**
     * Create a new instance of <code>ConfigLanguage</code> with the languages
     * selected from <code>Locale</code> class.
     *
     * @param locales iterable locale.
     * @throws IOException if the default properties not exists.
     */
    public ConfigLanguage(Iterable<Locale> locales) throws IOException {
        this.propLanguage = new Properties();
        Iterator<Locale> iterator = locales.iterator();
        boolean load = false;
        while (iterator.hasNext()) {
            Locale locale = iterator.next();
            if (isExists(locale.toString())) {
                initialize(locale.toString());
                load = true;
                break;
            }
        }

        if (!load)
            initialize(DEFAULT_LANGUAGE);
    }

    /**
     * Configurate properties file for language selected.
     *
     * @param language language selected.
     */
    private void initialize(String language) throws IOException {
        InputStream in;
        if (isExists(language)) {
            final String SPECIFIC_PATH = String.format(FORMAT_PATH_FILE, language);
            in = getClass().getClassLoader().getResourceAsStream(SPECIFIC_PATH);
        } else {
            final String DEFAULT_PATH = String.format(FORMAT_PATH_FILE, DEFAULT_LANGUAGE);
            in = getClass().getClassLoader().getResourceAsStream(DEFAULT_PATH);
        }
        propLanguage.load(in);
    }

    /**
     * Get true or false if exists or not the configuration file for the given
     * language.
     *
     * @param lang language.
     * @return true, if the language exists.
     */
    private boolean isExists(String lang) {
        final String SPECIFIC_PATH = String.format(FORMAT_PATH_FILE, lang);
        return getClass().getClassLoader().getResource(SPECIFIC_PATH) != null;
    }

    /**
     * Get string from the given key, if the key not exists then return null.
     *
     * @param key key.
     * @return string.
     */
    public String getString(String key) {
        return propLanguage.getProperty(key, null);
    }

}
