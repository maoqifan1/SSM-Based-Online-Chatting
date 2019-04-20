package com.mao.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    @Override
    public Date parse(String source, Locale locale) throws ParseException {
        return simpleDateFormat.parse(source);      // Formatter can only convert String
    }

    @Override
    public String print(Date Object, Locale locale) {
        return simpleDateFormat.format(Object);
    }
}
