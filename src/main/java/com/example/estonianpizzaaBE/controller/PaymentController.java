package com.example.estonianpizzaaBE.controller;

import com.example.estonianpizzaaBE.model.payment.Payment;
import com.example.estonianpizzaaBE.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PaymentController.REQUEST_MAPPING_URL)
public class PaymentController {

    public static final String REQUEST_MAPPING_URL = "/payment";
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        //TODO create payment from pending order, see what they can send you.
        // Requestbody might change, need to ask what they will send
        // Auth?
        Payment createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, null, 201);
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<Payment> paidPayment(@PathVariable Long id) {
        // Auth?
        Payment payment = paymentService.setPaymentToPaid(id);
        return new ResponseEntity<>(payment, null, 200);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Payment> cancelPayment(@PathVariable Long id) {
        Payment payment = paymentService.setPaymentToCanceled(id);
        return new ResponseEntity<>(payment, null, 200);
    }

}

