package org.example;

import lombok.NoArgsConstructor;
import org.example.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class PayrollDatabase {

    private static Map<Integer, Employee> itsEmployee = new HashMap<>();
    private static  Map<Integer, Employee> itsUnionMember = new HashMap<>();

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

    public static Employee getUnionMember(int memberId) {
        return itsUnionMember.get(memberId);
    }
    public static void addUnionMember(int memberId, Employee employee) {
        itsUnionMember.put(memberId, employee);
    }

    public static void removeUnionMember(int memberId) {
        itsUnionMember.remove(memberId);
    }

    public static List<Integer> getAllEmployeeIds() {
        return new ArrayList<>(itsEmployee.keySet());
    }
}