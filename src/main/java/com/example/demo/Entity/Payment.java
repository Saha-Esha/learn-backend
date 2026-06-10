package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private String studentId;
    private String courseId;

    private String status; // PAID | FAILED | PENDING
    private double amount;

    private LocalDateTime paymentDate;

    public Payment() {}

    public Payment(String studentId, String courseId, double amount, String status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = LocalDateTime.now();
    }

    // Getters & Setters
    public String getId() { return id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
}
