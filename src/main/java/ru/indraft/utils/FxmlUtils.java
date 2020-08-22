package ru.indraft.utils;

import javafx.fxml.FXMLLoader;
import ru.indraft.service.LocaleService;

import java.io.IOException;

public class FxmlUtils {

    public static <T> T loadView(String pathToView) {
        try {
            return FXMLLoader.load(
                    FxmlUtils.class.getResource(pathToView),
                    LocaleService.getInstance().getResourceBundle()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
