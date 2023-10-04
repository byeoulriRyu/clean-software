package org.example;

import lombok.NoArgsConstructor;
import org.example.entity.Employee;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class PayrollDatabase {

    private static Map<Integer, Employee> itsEmployee = new HashMap<>();

    public static Employee getEmployee(int empId) {
        return itsEmployee.get(empId);
    }

    public static void addEmployee(int empId, Employee employee) {
        itsEmployee.put(empId, employee);
    }

    public static void clear() {
        itsEmployee.clear();
    }

    public static void deleteEmployee(int empId) {
        itsEmployee.remove(empId);
    }

}