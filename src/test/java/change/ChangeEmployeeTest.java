package change;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddCommissionedEmployee;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.addEmployee.AddSalariedEmployee;
import org.example.classification.CommissionedClassification;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.classification.SalariedClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.BiweeklySchedule;
import org.example.schedule.MonthlySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.change.ChangeAddressTransaction;
import org.example.transaction.change.ChangeNameTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ChangeEmployeeTest {

    private static final int empId = 2;
    private static final String name = "Bill";
    private static final String address = "Home";

    @BeforeAll
    public static void before() throws Exception {
        PayrollDatabase.clear();

        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, name, address, 15.25);
        ahe.excute();
    }

    @Test
    @Order(1)
    public void testChangeNameTransaction() throws Exception {

        String changeName = "Bob";

        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, changeName);
        cnt.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);
        Assertions.assertEquals(changeName, e.getName());

    }

    @Test
    @Order(2)
    public void testChangeAddressTransaction() throws Exception {

        String changeAddress = "Home2";

        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, changeAddress);
        cat.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);
        Assertions.assertEquals(changeAddress, e.getAddress());

    }


}
