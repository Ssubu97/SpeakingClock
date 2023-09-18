package com.shubhananda.speakingClock.controller;

import com.shubhananda.speakingClock.service.SpeakingClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clock")
public class SpeakingClockController {

    private final SpeakingClockService clockService;

    @Autowired
    public SpeakingClockController(SpeakingClockService clockService) {
        this.clockService = clockService;
    }

    @GetMapping("/convertTime")
    public ResponseEntity<String> convertTimeToWords(@RequestParam String time) {
        try {
            String result = clockService.convertTimeToWords(time);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCurrentTime")
    public ResponseEntity<String> getCurrentTimeInWords() {
        String result = clockService.getCurrentTimeInWords();
        return ResponseEntity.ok(result);
    }

}

