package ru.indraft.utils;

public class ConvertUtils {

    public static <I extends Enum<I>, T extends Enum<T>> T convertEnum(Class<T> targetType, I source) {
        if (source == null) {
            return null;
        }
        try {
            return T.valueOf(targetType, source.name());
        } catch (IllegalArgumentException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
