package org.example.method;

import lombok.Builder;
import org.example.transaction.pay.PayCheck;

@Builder
public class HoldMethod implements PaymentMethod {

    @Override
    public void pay(PayCheck pc) {

    }
}
