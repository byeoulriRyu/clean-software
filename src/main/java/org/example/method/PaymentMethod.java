package org.example.method;

import org.example.transaction.pay.PayCheck;

public interface PaymentMethod {
    void pay(PayCheck pc);
}
