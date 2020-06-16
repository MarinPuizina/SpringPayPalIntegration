package com.marin.paypal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayPalController {

    final PayPalService paypalService;

    public PayPalController(PayPalService paypalService) {
        this.paypalService = paypalService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
