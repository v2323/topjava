package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter {
    public static final class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String text, Locale locale) throws ParseException {
            if(text.length()==0){
            return null;}
            return LocalDate.parse(text);
        }

        @Override
        public String print(LocalDate object, Locale locale) {
            return object.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static final class LocalTimeFormatter implements Formatter<LocalTime> {

        @Override
        public LocalTime parse(String text, Locale locale) throws ParseException {
            if(text.length()==0){
                return null;}
            return LocalTime.parse(text);
        }

        @Override
        public String print(LocalTime object, Locale locale) {
            return object.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
}
