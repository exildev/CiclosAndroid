package com.nativapps.arpia.service;

import com.nativapps.arpia.model.ConfigLanguage;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class GenericService implements Service {

    private static final Logger LOG = Logger
            .getLogger(GenericService.class.getName());

    /**
     * Configuration language.
     */
    protected ConfigLanguage config;

    @Override
    public void configurate(Iterable<Locale> locales) {
        try {
            config = new ConfigLanguage(locales);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error loading configuration file, Cause: "
                    + ex.getCause().getMessage(), ex);
        }
    }

    @Override
    public void configurate(Locale locale) {
        try {
            config = new ConfigLanguage(locale);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error loading configuration file, Cause: "
                    + ex.getCause().getMessage(), ex);
        }
    }

    /**
     * Get configuration language.
     *
     * @return configuration language instance.
     */
    public ConfigLanguage getConfig() {
        return config;
    }

}
