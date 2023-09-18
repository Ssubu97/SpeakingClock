package com.shubhananda.speakingClock.service;


import com.shubhananda.speakingClock.exception.InvalidTimeFormatException;
import com.shubhananda.speakingClock.exception.SpecialTimeException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SpeakingClockService {

    public String convertTimeToWords(String time) {
        if (time.equals("12:00")) {
            throw new SpecialTimeException("It's Midday");
        } else if (time.equals("00:00")) {
            throw new SpecialTimeException("It's Midnight");
        } else {
            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            String hourWords = getHourInWords(hour);
            String minuteWords = getMinuteInWords(minute);

            return "It's " + hourWords + " " + minuteWords;
        }
    }

    public String getHourInWords(int hour) {
        if (hour < 0 || hour > 23) {
            throw new InvalidTimeFormatException("Invalid hour: " + hour);
        }

        String[] hourWords = {
                "Midnight", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"
        };

        if (hour >= 13) {
            hour -= 12;
        }

        return hourWords[hour];
    }

    public String getMinuteInWords(int minute) {
        if (minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid minute: " + minute);
        }

        if (minute == 0) {
            return "o'clock";
        } else if (minute < 10) {
            return "oh " + getDigitInWords(minute);
        } else if (minute < 20) {
            String[] teens = {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
            return teens[minute - 10];
        } else {
            String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty"};
            return tens[minute / 10] + (minute % 10 != 0 ? " " + getDigitInWords(minute % 10) : "");
        }
    }

    private String getDigitInWords(int digit) {
        String[] digitsInWords = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return digitsInWords[digit];
    }

    public String getCurrentTimeInWords() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        return convertTimeToWords(currentTime);
    }
}

