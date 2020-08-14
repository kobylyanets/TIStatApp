package ru.indraft.service;

import java.util.ResourceBundle;

public class LocaleService {

    private static final String BUNDLE_NAME = "bundles.messages";

    private static final LocaleService instance = new LocaleService();
    private final ResourceBundle resourceBundle;

    private LocaleService() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public static LocaleService getInstance() {
        return instance;
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
