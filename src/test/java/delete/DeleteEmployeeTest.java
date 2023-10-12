package delete;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddCommissionedEmployee;
import org.example.entity.Employee;
import org.example.transaction.delete.DeleteEmployeeTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeleteEmployeeTest {

    @BeforeAll
    public static void before() {
        PayrollDatabase.clear();
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        int empId = 3;
        String name = "Lance";
        String address = "Home";
        double salary = 2500;
        double commissionRate = 3.2;

        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
        addCommissionedEmployee.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.excute();

        e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNull(e);

    }

}
