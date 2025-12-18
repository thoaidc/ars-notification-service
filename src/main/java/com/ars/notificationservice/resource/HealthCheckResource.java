package com.ars.notificationservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckResource {

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
    }
}
