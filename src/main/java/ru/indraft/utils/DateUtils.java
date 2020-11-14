package ru.indraft.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String DATE_OF_FOUNDATION_TI = "01.10.2016";
    public static final String START_TIME_OF_DAY = "00:00:00";
    public static final String END_TIME_OF_DAY = "23:59:59";
    public static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");


    public static LocalDateTime getLocalDateTimeOfFoundationTI() {
        return LocalDateTime.of(
                LocalDate.parse(DATE_OF_FOUNDATION_TI, dateFormatter),
                getStartOfDayTime()
        );
    }

    public static LocalDateTime getLocalEndTimeOfCurrentDay() {
        return LocalDateTime.of(LocalDate.now(), getEndOfDayTime());
    }

    public static OffsetDateTime getOffsetDateOfFoundationTI() {
        return OffsetDateTime.of(getLocalDateTimeOfFoundationTI(), ZoneOffset.UTC);
    }

    public static OffsetDateTime getOffsetEndTimeOf2016Year() {
        return OffsetDateTime.of(LocalDateTime.of(
                LocalDate.parse("01.10.2017", dateFormatter),
                getStartOfDayTime()), ZoneOffset.UTC);
    }

    public static OffsetDateTime getOffsetEndTimeOf2017Year() {
        return OffsetDateTime.of(LocalDateTime.of(
                LocalDate.parse("01.10.2017", dateFormatter),
                getStartOfDayTime()), ZoneOffset.UTC);
    }

    public static OffsetDateTime getOffsetEndTimeOf2018Year() {
        return OffsetDateTime.of(LocalDateTime.of(
                LocalDate.parse("01.10.2018", dateFormatter),
                getStartOfDayTime()), ZoneOffset.UTC);
    }

    public static OffsetDateTime getOffsetEndTimeOf2019Year() {
        return OffsetDateTime.of(LocalDateTime.of(
                LocalDate.parse("01.10.2019", dateFormatter),
                getStartOfDayTime()), ZoneOffset.UTC);
    }

    public static LocalTime getStartOfDayTime() {
        return LocalTime.parse(START_TIME_OF_DAY, timeFormatter);
    }

    public static LocalTime getEndOfDayTime() {
        return LocalTime.parse(END_TIME_OF_DAY, timeFormatter);
    }

    public static OffsetDateTime getOffsetEndTimeOfCurrentDay() {
        return OffsetDateTime.of(getLocalEndTimeOfCurrentDay(), ZoneOffset.UTC);
    }

}
