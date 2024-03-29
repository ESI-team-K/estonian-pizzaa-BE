package com.example.estonianpizzaaBE.controller;

import com.example.estonianpizzaaBE.model.Invoice;
import com.example.estonianpizzaaBE.model.MenuItem;
import com.example.estonianpizzaaBE.model.payment.CardType;
import com.example.estonianpizzaaBE.model.payment.Payment;
import com.example.estonianpizzaaBE.service.PaymentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping(PaymentController.REQUEST_MAPPING_URL)
public class PaymentController {

    public static final String REQUEST_MAPPING_URL = "/api/payment";
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPayment(id);
        return new ResponseEntity<>(payment, null, 200);
    }

    @PostMapping("/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody List<MenuItem> menuItems) {
        // Requestbody might change, need to ask what they will send
        // Auth?
        Invoice invoice = paymentService.createInvoice(menuItems);
        return new ResponseEntity<>(invoice, null, 201);
    }

    @PostMapping("/{id}/pay/card")
    public ResponseEntity<Payment> payByCard(@PathVariable Long id, @RequestBody CardPaymentInfo cardPaymentInfo) {
        // Auth?
        Payment payment = paymentService.payByCard(id, cardPaymentInfo);
        // TODO: send confirm order request
        return new ResponseEntity<>(payment, null, 200);
    }

    @PutMapping("/{id}/pay/cash")
    public ResponseEntity<Payment> payByCash(@PathVariable Long id) {
        // Auth?
        Payment payment = paymentService.payByCash(id);
        return new ResponseEntity<>(payment, null, 200);
    }

    @PatchMapping("/{id}/cancel")
    // Auth?
    public ResponseEntity<Payment> cancelPayment(@PathVariable Long id) {
        Payment payment = paymentService.setPaymentToCanceled(id);
        return new ResponseEntity<>(payment, null, 200);
    }

    @PatchMapping("/{id}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable Long id) {
        Payment payment = paymentService.refundPayment(id);
        return new ResponseEntity<>(payment, null, 200);
    }

    @GetMapping
    public ResponseEntity<Iterable<Payment>> getPayments() {
        Iterable<Payment> payments = paymentService.getPayments();
        return new ResponseEntity<>(payments, null, 200);
    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id) {
        Invoice invoice = paymentService.getInvoice(id);
        return new ResponseEntity<>(invoice, null, 200);
    }


    @Getter
    public static class CardPaymentInfo {
        String cardNumber;
        String cardHolder;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
        YearMonth cardExpiration;
        CardType cardType;
    }
}

