package com.br.biosentinela.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String root() {
        return "API BioSentinela ativa ✅";
    }

    @GetMapping("/health")
    public String health() {
        return "Status: OK";
    }
}
