package org.example.transaction.pay;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class PayCheck {

    private Date payDate;
    private double grossDay;
    private double deductions;
    private double netPay;
    
    @Builder.Default
    private Map<String, String> fieldMap = new HashMap<>();

    public String getField(String fieldName) {
        return fieldMap.get(fieldName);
    }
    
    public double getCrossPay() {
        //TODO: 메서드 구현 필요
        return 0.0;
    }
    
    public Date getPayPeriodEndDate() {
        //TODO: 메서드 구현 필요
        return new Date();
    }
}
