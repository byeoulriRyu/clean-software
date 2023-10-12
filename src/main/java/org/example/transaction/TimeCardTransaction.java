package org.example.transaction;

import lombok.Builder;
import org.example.PayrollDatabase;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.TimeCard;

@Builder
public class TimeCardTransaction implements Transaction{
    private int empId;
    private long date;
    private double hours;

    @Override
    public void excute() throws Exception {

        Employee emp = PayrollDatabase.getEmployee(empId);

        if (emp == null)
            throw new Exception("No such employee.");

        PaymentClassification pc = emp.getClassification();

        try {
            HourlyClassification hc = (HourlyClassification) pc;
            hc.addTimeCard(
                    TimeCard.builder()
                            .hours(hours)
                            .date(date)
                            .build()
            );
        } catch (Exception e) {
            throw new Exception("Tried to add timecard to non-hourly employee");
        }

    }

}
