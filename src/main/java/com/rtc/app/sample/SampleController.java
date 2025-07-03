package com.rtc.app.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/sample")
    public ResponseEntity<Void> sample() {
        return ResponseEntity.ok().build();
    }
}