package org.example.transaction.change;

import lombok.NoArgsConstructor;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.schedule.PaymentSchedule;

@NoArgsConstructor
public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();
    abstract PaymentSchedule getSchedule();
}
