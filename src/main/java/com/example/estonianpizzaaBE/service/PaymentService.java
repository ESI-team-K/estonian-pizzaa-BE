package com.example.estonianpizzaaBE.service;

import com.example.estonianpizzaaBE.exception.payment.InvalidPaymentStatusException;
import com.example.estonianpizzaaBE.exception.payment.PaymentNotFoundException;
import com.example.estonianpizzaaBE.model.payment.Payment;
import com.example.estonianpizzaaBE.model.payment.PaymentStatus;
import com.example.estonianpizzaaBE.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        // TODO create payment from pending order, see what they can send you.
        // Requestbody might change, need to ask what they will send
        return paymentRepository.save(payment);
    }

    public Payment setPaymentToPaid(Long id) throws PaymentNotFoundException {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);

        throwIfStatusNotPending(payment);

        payment.setStatus(PaymentStatus.PAID);
        return paymentRepository.save(payment);
    }

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
}
