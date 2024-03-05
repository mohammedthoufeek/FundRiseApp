package com.miniProject.fundriseapp.payments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payments,Integer> {

}
