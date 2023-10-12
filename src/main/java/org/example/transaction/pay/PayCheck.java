package org.example.transaction.pay;

import lombok.*;
import org.example.PayrollDatabase;
import org.example.entity.Employee;
import org.example.transaction.Transaction;

import java.util.Date;

@Getter
@Setter
@Builder
public class PayCheck {

    private Date payDate;
    private double grossDay;
    private double deductions;
    private double netPay;

    public String getDisposition() {
        return "";
    }
}
