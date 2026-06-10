package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Payment;
import com.example.demo.Repository.PaymentRepo;
import com.example.demo.Service.PaymentAsyncService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentRepo paymentRepo;
    private PaymentAsyncService paymentAsyncService;


    // ✅ Make payment
    @PostMapping("/pay")
    public String makePayment(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam double amount) {

        // Check if already paid
        boolean alreadyPaid = paymentRepo
            .existsByStudentIdAndCourseIdAndStatus(
                studentId, courseId, "PAID"
            );

        if (alreadyPaid) {
            return "Student already purchased this course";
        }

        Payment payment = new Payment(
            studentId,
            courseId,
            amount,
            "PAID" // Simulated success
        );

        paymentRepo.save(payment);

        //  paymentAsyncService.processPayment(payment.getId());
        return "Payment successful";
    }

    // ✅ Check payment status
    @GetMapping("/status")
    public String checkPaymentStatus(
            @RequestParam String studentId,
            @RequestParam String courseId) {

        Optional<Payment> payment =
            paymentRepo.findByStudentIdAndCourseId(studentId, courseId);

        return payment.map(Payment::getStatus).orElse("NOT_PAID");
    }

    // ✅ Check access to course
    @GetMapping("/access")
    public boolean hasAccess(
            @RequestParam String studentId,
            @RequestParam String courseId) {

        return paymentRepo.existsByStudentIdAndCourseIdAndStatus(
            studentId, courseId, "PAID"
        );
    }
}
