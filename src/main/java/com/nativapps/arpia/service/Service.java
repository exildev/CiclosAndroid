package com.nativapps.arpia.service;

import java.util.Locale;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface Service {

    /**
     * Configurate a service for internationalization.
     *
     * @param locales list a locales.
     */
    void configurate(Iterable<Locale> locales);

    /**
     * Configurate a service for internationalization.
     *
     * @param locale locale
     */
    void configurate(Locale locale);

}
