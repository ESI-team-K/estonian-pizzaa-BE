package com.example.estonianpizzaaBE.service;

import com.example.estonianpizzaaBE.controller.PaymentController;
import com.example.estonianpizzaaBE.exception.payment.InvalidCardException;
import com.example.estonianpizzaaBE.exception.payment.InvalidPaymentStatusException;
import com.example.estonianpizzaaBE.exception.payment.PaymentNotFoundException;
import com.example.estonianpizzaaBE.model.Invoice;
import com.example.estonianpizzaaBE.model.MenuItem;
import com.example.estonianpizzaaBE.model.payment.*;
import com.example.estonianpizzaaBE.repository.InvoiceRepository;
import com.example.estonianpizzaaBE.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    public Invoice createInvoice(List<MenuItem> menuItems) {
        Invoice invoice = new Invoice();
        invoice.setCreated(Instant.now());
        invoice.setMenuItems(menuItems.stream().map(MenuItem::getId).collect(Collectors.toSet()));
        // TODO Calculate fee
        // TODO calculate total
        Payment payment = createPayment(invoice);
        invoice.setPayment(payment);
        return invoiceRepository.save(invoice);
    }

    public Payment createPayment(Invoice invoice) {
        Payment payment = new Payment();
        payment.setCreated(invoice.getCreated());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMethod(PaymentMethod.UNKNOWN);
        return paymentRepository.save(payment);
    }

    public Payment payByCard(Long id, PaymentController.CardPaymentInfo cardPaymentInfo) {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);

        throwIfStatusNotPending(payment);
        throwIfInvalidCard(cardPaymentInfo);

        // This is where actual payment would go, however we just emulate it
        PaymentCard paymentCard = createPaymentCard(cardPaymentInfo);

        payment.setStatus(PaymentStatus.PAID);
        payment.setMethod(PaymentMethod.CARD);
        payment.setPaymentCard(paymentCard);
        return paymentRepository.save(payment);
    }

    public Payment payByCash(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);

        throwIfStatusNotPending(payment);

        payment.setStatus(PaymentStatus.PAID);
        payment.setMethod(PaymentMethod.CASH);
        return paymentRepository.save(payment);
    }

    private PaymentCard createPaymentCard(PaymentController.CardPaymentInfo cardPaymentInfo) {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setLastNumbers(cardPaymentInfo.getCardNumber().substring(cardPaymentInfo.getCardNumber().length() - 4));
        paymentCard.setCardType(cardPaymentInfo.getCardType());
        paymentCard.setCardHolderName(cardPaymentInfo.getCardHolder());
        paymentCard.setExpirationDate(cardPaymentInfo.getCardExpiration());
        // Generate 23-digit reference number
        paymentCard.setReferenceNumber(String.format("%023d", (int) (Math.random() * Math.pow(10, 23))));
        return paymentCard;
    }

    private void throwIfInvalidCard(PaymentController.CardPaymentInfo cardPaymentInfo) {
        // Check if card number is valid
        if (cardPaymentInfo.getCardNumber().length() != 16) throw new InvalidCardException();
        // Check if card holder is valid
        if (cardPaymentInfo.getCardHolder().length() < 3) throw new InvalidCardException();
        // Check if card expiration is valid
        if (cardPaymentInfo.getCardExpiration().isBefore(YearMonth.now())) throw new InvalidCardException();
        // Check if card type is valid
        if (!cardPaymentInfo.getCardType().equals(CardType.VISA) && !cardPaymentInfo.getCardType().equals(CardType.MASTERCARD))
            throw new InvalidCardException();
    }

//    public Payment setPaymentToPaid(Long id) throws PaymentNotFoundException {
//        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
//
//        throwIfStatusNotPending(payment);
//
//        payment.setStatus(PaymentStatus.PAID);
//        return paymentRepository.save(payment);
//    }

    public Payment setPaymentToCanceled(Long id) throws PaymentNotFoundException {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);

        throwIfStatusNotPending(payment);

        payment.setStatus(PaymentStatus.CANCELLED);
        return paymentRepository.save(payment);
    }

    private void throwIfStatusNotPending(Payment payment) {
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new InvalidPaymentStatusException();
        }
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
    }

    public Payment refundPayment(Long id) {
        // Replace logic when actual payment implemented
        setPaymentToCanceled(id);
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
    }
}
