package change;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddCommissionedEmployee;
import org.example.classification.CommissionedClassification;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.classification.SalariedClassification;
import org.example.entity.Employee;
import org.example.schedule.BiweeklySchedule;
import org.example.schedule.MonthlySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.change.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ChangeClassificationTest {

    private static final int empId = 3;


    @BeforeAll
    public static void before() throws Exception {
        PayrollDatabase.clear();

        String name = "Lance";
        String address = "Home";
        double salary = 2500;
        double commissionRate = 3.2;

        AddCommissionedEmployee ace = new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
        ace.execute();
    }

    @Test
    @Order(1)
    public void testChangeHourlyTransaction() throws Exception {

        double hourlyRate = 27.52;

        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, hourlyRate);
        cht.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assertions.assertNotNull(pc);

        HourlyClassification hc = (HourlyClassification) pc;
        Assertions.assertNotNull(hc);
        Assertions.assertEquals(hourlyRate, hc.getHourlyRate());

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        Assertions.assertNotNull(ws);
    }

    @Test
    @Order(2)
    public void testChangeSalariedTransaction() throws Exception {

        double changeSalary = 2800;

        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, changeSalary);
        cst.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assertions.assertNotNull(pc);

        SalariedClassification sc = (SalariedClassification) pc;
        Assertions.assertNotNull(sc);
        Assertions.assertEquals(changeSalary, sc.getSalary());

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        Assertions.assertNotNull(ms);

    }

    @Test
    @Order(3)
    public void testChangeCommissionedTransaction() throws Exception {

        double salary = 2700;
        double changeCommissionRate = 3.5;

        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, salary, changeCommissionRate);
        cct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assertions.assertNotNull(pc);

        CommissionedClassification cc = (CommissionedClassification) pc;
        Assertions.assertNotNull(cc);
        Assertions.assertEquals(salary, cc.getSalary());
        Assertions.assertEquals(changeCommissionRate, cc.getCommissionRate());

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;
        Assertions.assertNotNull(bs);

    }


}
