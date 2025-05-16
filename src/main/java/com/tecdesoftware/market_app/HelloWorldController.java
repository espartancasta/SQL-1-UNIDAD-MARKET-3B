package com.tecdesoftware.market_app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HelloWorldController {

    @RequestMapping("/hola")
    public String saludar() {
        return "BYE WOLRD JAIME";
    }
}
