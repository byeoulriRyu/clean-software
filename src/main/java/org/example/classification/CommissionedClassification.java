package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.SalesReceipt;
import org.example.entity.TimeCard;
import org.example.transaction.pay.PayCheck;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class CommissionedClassification implements PaymentClassification {
    private double salary;
    private double commissionRate;

    @Builder.Default
    private Map<Long, SalesReceipt> salesReceiptMap = new LinkedHashMap<>();

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceiptMap.put(salesReceipt.getDate(), salesReceipt);
    }

    public SalesReceipt getSalesReceipt(long date) {
        return salesReceiptMap.get(date);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        return 0;
    }
}
