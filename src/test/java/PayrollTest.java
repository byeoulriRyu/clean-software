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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PayrollTest {

    @Test
    public void testAddSalariedEmployee() throws Exception {
        int empId = 1;
        String name = "Bob";
        String address = "Home";
        double salary = 1000.00;

        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, name, address, salary);
        addSalariedEmployee.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;

        Assertions.assertEquals(salary, sc.getSalary(), .001);

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;

    }

    @Test
    public void testAddHourlyEmployee() throws Exception {
        int empId = 2;
        String name = "Star";
        String address = "Home";
        double hourlyRate = 50;

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, name, address, hourlyRate);
        addHourlyEmployee.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;

        Assertions.assertEquals(hourlyRate, hc.getHourlyRate(), .001);

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;

    }

    @Test
    public void testAddCommissionedEmployee() throws Exception {
        int empId = 3;
        String name = "Ryu";
        String address = "Home";
        double salary = 1000.00;
        double commissionRate = 10;

        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
        addCommissionedEmployee.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;

        Assertions.assertEquals(salary, cc.getSalary(), .001);
        Assertions.assertEquals(commissionRate, cc.getCommissionRate(), .001);

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;

    }

}
