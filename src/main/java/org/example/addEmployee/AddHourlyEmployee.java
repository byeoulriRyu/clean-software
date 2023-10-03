package org.example.addEmployee;

import lombok.NoArgsConstructor;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.AddEmployeeTransaction;

@NoArgsConstructor
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private double hourlyRate;

    public AddHourlyEmployee(int empId, String address, String name, double hourlyRate) {
        super(empId, address, name);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
