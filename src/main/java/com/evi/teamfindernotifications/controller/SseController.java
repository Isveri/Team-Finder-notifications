package com.evi.teamfindernotifications.controller;

import com.evi.teamfindernotifications.service.SseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notify")
public class SseController {

    private final SseService sseService;

    @GetMapping
    public SseEmitter notifyUser() throws IOException {
        return sseService.createEmitter();
    }

}
