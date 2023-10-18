package org.example.method;

import lombok.Builder;
import lombok.Getter;
import org.example.transaction.pay.PayCheck;

@Getter
@Builder
public class MailMethod implements PaymentMethod {

    private String address;

    @Override
    public void pay(PayCheck pc) {
        pc.putField("Disposition", "Mail");
    }
}
