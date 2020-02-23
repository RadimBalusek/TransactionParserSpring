package cz.rbalusek.transactionparser.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/*
 Author: Radim Balusek
 */
@Service
public class DateTimeUtils {

    // check valid format date ex.: 2015-07-22 23:59:59

    private static Pattern DATE_PATTERN = Pattern.compile(
            "\\d{4}-\\d{2}-\\d{2}\\s*\\d{2}:\\d{2}:\\d{2}");

    private static Pattern PHONE_PATTERN = Pattern.compile(
            "^[A-Za-z]*\\s*?\\/{1}\\s*\\d{9}");

    public boolean dateMatches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    public boolean phoneMatches(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public Date convertToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
