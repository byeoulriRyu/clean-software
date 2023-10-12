package org.example.transaction;

import lombok.Builder;
import org.example.PayrollDatabase;
import org.example.classification.CommissionedClassification;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.SalesReceipt;
import org.example.entity.TimeCard;

@Builder
public class SalesReceiptTransaction implements Transaction{
    private int empId;
    private long date;
    private int amount;

    @Override
    public void excute() throws Exception {

        Employee emp = PayrollDatabase.getEmployee(empId);

        if (emp == null)
            throw new Exception("No such employee.");

        PaymentClassification pc = emp.getClassification();

        try {
            CommissionedClassification cc = (CommissionedClassification) pc;
            cc.addSalesReceipt(
                    SalesReceipt.builder()
                            .date(date)
                            .amount(amount)
                            .build()
            );
        } catch (Exception e) {
            throw new Exception("Tried to add timecard to non-hourly employee");
        }

    }

}
