package com.learning.learningSpringBoot.InternalWorking;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

//registers your class as a Spring Bean.
@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "razorpay")
public class RazorpayPaymentService implements PaymentService{

    @Override
    public String pay(){
    String payment="Razorpay Payment";
    System.out.println("Payment from: "+payment);
    return payment;
    }
}

