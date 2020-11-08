package ru.indraft.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtils.class);

    public static <I extends Enum<I>, T extends Enum<T>> T convertEnum(Class<T> targetType, I source) {
        if (source == null) {
            return null;
        }
        try {
            return T.valueOf(targetType, source.name());
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }

}
