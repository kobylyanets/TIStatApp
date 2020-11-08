package ru.indraft.utils;

import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.service.LocaleService;

import java.io.IOException;

public class FxmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxmlUtils.class);

    public static <T> T loadView(String pathToView) {
        try {
            return FXMLLoader.load(
                    FxmlUtils.class.getResource(pathToView),
                    LocaleService.getInstance().getResourceBundle()
            );
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return null;
    }
}
