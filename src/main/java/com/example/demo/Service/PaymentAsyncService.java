package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Payment;
import com.example.demo.Repository.PaymentRepo;
@Service
public class PaymentAsyncService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Async("taskExecutor")
    public void processPayment(String paymentId) {
        try {
            // simulate gateway delay
            Thread.sleep(3000);

            Payment payment = paymentRepo
                .findById(paymentId)
                .orElseThrow();

            payment.setStatus("PAID");
            paymentRepo.save(payment);

            System.out.println("Payment completed: " + paymentId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
