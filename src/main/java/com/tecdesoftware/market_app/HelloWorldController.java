package com.tecdesoftware.market_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestControllergit add .

@RequestMapping("/saludar")
public class HelloWorldController {

    @GetMapping("/hola")
    public String saludar() {
        return "Hi World, by EnriqueCasta";
    }
}