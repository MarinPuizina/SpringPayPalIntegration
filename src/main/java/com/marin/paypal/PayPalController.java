package com.marin.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayPalController {

    final PayPalService paypalService;

    public static final String LOCALHOST = "http://localhost:9090/";
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    public PayPalController(PayPalService paypalService) {
        this.paypalService = paypalService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {

        try {
            Payment payment = paypalService.createPayment(order.getPrice(),
                    order.getCurrency(),
                    order.getMethod(),
                    order.getIntent(),
                    order.getDescription(),
                    LOCALHOST + CANCEL_URL,
                    LOCALHOST + SUCCESS_URL);

            for (Links link : payment.getLinks()) {

                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }

            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

}
