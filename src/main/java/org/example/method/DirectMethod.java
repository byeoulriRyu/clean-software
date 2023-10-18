package org.example.method;

import lombok.Builder;
import lombok.Getter;
import org.example.transaction.pay.PayCheck;

@Getter
@Builder
public class DirectMethod implements PaymentMethod {

    private String bank;
    private String account;

    @Override
    public void pay(PayCheck pc) {
        pc.putField("Disposition", "Direct");

    }
}
