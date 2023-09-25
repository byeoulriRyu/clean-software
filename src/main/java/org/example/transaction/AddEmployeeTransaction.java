package org.example.transaction;

import org.example.PayrollDatabase;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;

public abstract class AddEmployeeTransaction implements Transaction{
    private int empId;
    private String name;
    private String address;

    public AddEmployeeTransaction() {
    }

    public AddEmployeeTransaction(int empId, String address, String name) {
        this.empId = empId;
        this.address = address;
        this.name = name;
    }

    public abstract PaymentClassification getClassification();
    public abstract PaymentSchedule getSchedule();

    @Override
    public void excute() throws Exception {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee employee = new Employee(empId, address, name);
        employee.setClassification(pc);
        employee.setSchedule(ps);
        employee.setMethod(pm);
        PayrollDatabase.addEmployee(empId, employee);
    }

}
