package org.example.addEmployee;

import lombok.NoArgsConstructor;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.add.AddEmployeeTransaction;

@NoArgsConstructor
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private double hourlyRate;

    public AddHourlyEmployee(int empId, String address, String name, double hourlyRate) {
        super(empId, address, name);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return HourlyClassification.builder()
                .hourlyRate(hourlyRate)
                .build();
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
