package com.learning.learningSpringBoot.InternalWorking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;

@SpringBootApplication
public class InternalWorkingOfSpringBootApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(InternalWorkingOfSpringBootApplication.class,args);
    }
//    private RazorpayPaymentService paymentService=new RazorpayPaymentService();   // Here is tightly coupled

//    there are 2 ways to injects dependency  that bean  needed.
//   1.  @Autowired
//    private  RazorpayPaymentService paymentService;

    // Remove coupling make it loose couple
    private final PaymentService paymentService;

//    2. Constructor DI

//    public InternalWorkingOfSpringBootApplication(RazorpayPaymentService paymentService) {
//        this.paymentService=paymentService;
//    }

public InternalWorkingOfSpringBootApplication(PaymentService paymentService) {
    this.paymentService=paymentService;
}

    @Override
    public void run(String... args) throws Exception{
        String payment=paymentService.pay();
        System.out.println("Payment Done : "+payment);
    }


}
