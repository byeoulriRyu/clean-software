package org.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.PayrollDatabase;
import org.example.affiliation.Affiliation;
import org.example.classification.PaymentClassification;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;
import org.example.transaction.pay.PayCheck;

import java.util.Date;


@Getter
@Setter
@Builder
public class Employee {

    private int empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;

    public boolean isPayDate(Date payDate) {
        return schedule.isPayDate(payDate);
    }

    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return schedule.getPayPeriodStartDate(payPeriodEndDate);
    }

    public void payDay(PayCheck pc) {
        double grossPay = classification.calculatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        method.pay(pc);
    }

}
