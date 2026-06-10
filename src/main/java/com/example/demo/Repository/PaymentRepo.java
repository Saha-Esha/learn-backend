package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Entity.Payment;

public interface PaymentRepo extends MongoRepository<Payment, String> {

    Optional<Payment> findByStudentIdAndCourseId(String studentId, String courseId);

    boolean existsByStudentIdAndCourseIdAndStatus(
        String studentId,
        String courseId,
        String status
    );
}
