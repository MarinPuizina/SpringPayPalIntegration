package com.marin.paypal;

import org.springframework.stereotype.Controller;

@Controller
public class PayPalController {

    final PayPalService paypalService;

    public PayPalController(PayPalService paypalService) {
        this.paypalService = paypalService;
    }

    

}
