package by.pwt.pilipenko.payments.dao.resource;

import java.util.ResourceBundle;

public class QueriesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");

    // класс извлекает информацию из файла config. properties
    private QueriesManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}