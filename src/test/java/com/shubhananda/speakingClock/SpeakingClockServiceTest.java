package com.shubhananda.speakingClock;

import com.shubhananda.speakingClock.exception.InvalidTimeFormatException;
import com.shubhananda.speakingClock.exception.SpecialTimeException;
import com.shubhananda.speakingClock.service.SpeakingClockService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SpeakingClockServiceTest {

    private SpeakingClockService clockService;

    @Before
    public void setUp() {
        clockService = new SpeakingClockService();
    }

    @Test
    public void testConvertTimeToWords() {
        // Test a valid time
        assertEquals("It's Midnight", clockService.convertTimeToWords("00:00"));

        // Test a special time (Midday)
        try {
            assertEquals("It's Midday", clockService.convertTimeToWords("12:00"));
            fail("Expected SpecialTimeException, but it didn't occur.");
        } catch (SpecialTimeException e) {
            assertEquals("It's Midday", e.getMessage());
        }

        // Test a valid time with various hours and minutes
        assertEquals("It's One o'clock", clockService.convertTimeToWords("01:00"));
        assertEquals("It's Three Thirty", clockService.convertTimeToWords("03:30"));

        // Test an invalid hour
        try {
            clockService.convertTimeToWords("24:00");
            fail("Expected InvalidTimeFormatException, but it didn't occur.");
        } catch (InvalidTimeFormatException e) {
            assertEquals("Invalid hour: 24", e.getMessage());
        }

        // Test an invalid minute
        try {
            clockService.convertTimeToWords("12:60");
            fail("Expected InvalidTimeFormatException, but it didn't occur.");
        } catch (InvalidTimeFormatException e) {
            assertEquals("Invalid minute: 60", e.getMessage());
        }
    }

    @Test
    public void testGetCurrentTimeInWords() {
        // Test getting the current time in words
        String currentTimeInWords = clockService.getCurrentTimeInWords();
        // We can't predict the exact value since it depends on the current time, so we'll just print it
        System.out.println("Current Time in Words: " + currentTimeInWords);
    }

    @Test
    public void testGetHourInWords() {
        // Test getting hours in words
        assertEquals("Midnight", clockService.getHourInWords(0));
        assertEquals("One", clockService.getHourInWords(1));
        assertEquals("Twelve", clockService.getHourInWords(12));
        assertEquals("One", clockService.getHourInWords(13));
        assertEquals("Ten", clockService.getHourInWords(22));

        // Test an invalid hour
        try {
            clockService.getHourInWords(24);
            fail("Expected InvalidTimeFormatException, but it didn't occur.");
        } catch (InvalidTimeFormatException e) {
            assertEquals("Invalid hour: 24", e.getMessage());
        }
    }

    @Test
    public void testGetMinuteInWords() {
        // Test getting minutes in words
        assertEquals("o'clock", clockService.getMinuteInWords(0));
        assertEquals("oh One", clockService.getMinuteInWords(1));
        assertEquals("Fifteen", clockService.getMinuteInWords(15));
        assertEquals("Twenty Five", clockService.getMinuteInWords(25));
        assertEquals("Fifty Nine", clockService.getMinuteInWords(59));

        // Test an invalid minute
        try {
            clockService.getMinuteInWords(60);
            fail("Expected InvalidTimeFormatException, but it didn't occur.");
        } catch (InvalidTimeFormatException e) {
            assertEquals("Invalid minute: 60", e.getMessage());
        }
    }
}
