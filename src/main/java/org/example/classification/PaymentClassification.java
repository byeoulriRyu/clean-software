package org.example.classification;

import org.example.transaction.pay.PayCheck;

public interface PaymentClassification {
    double calculatePay(PayCheck pc);
}
