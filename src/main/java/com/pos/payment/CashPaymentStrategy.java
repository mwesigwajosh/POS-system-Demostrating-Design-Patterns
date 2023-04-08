package com.pos.payment;
public class CashPaymentStrategy implements PaymentStrategy {
    public String pay() {
        return "cash";
    }
}
