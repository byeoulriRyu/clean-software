package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

    private double hourlyRate;

    public ChangeHourlyTransaction(int empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return HourlyClassification.builder().hourlyRate(hourlyRate).build();
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
