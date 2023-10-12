package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.transaction.pay.PayCheck;

@Getter
@Setter
@Builder
public class SalariedClassification implements PaymentClassification {

    private double salary;

    @Override
    public double calculatePay(PayCheck pc) {
        return salary;
    }
}
