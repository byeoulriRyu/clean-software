import org.example.PayrollDatabase;
import org.example.addEmployee.AddCommissionedEmployee;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.addEmployee.AddSalariedEmployee;
import org.example.classification.CommissionedClassification;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.SalesReceipt;
import org.example.entity.TimeCard;
import org.example.transaction.SalesReceiptTransaction;
import org.example.transaction.TimeCardTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SalesReceiptTransactionTest {

    @BeforeAll
    public static void before() {
        PayrollDatabase.clear();
    }

    @Test
    public void testTimeCardTransaction() throws Exception {
        int empId = 2;
        String name = "Bill";
        String address = "Home";
        double salary = 2800;
        double commissionRate = 3;
        long date = 20011031;
        int amount = 10;

        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, address, name, salary, commissionRate);
        addCommissionedEmployee.excute();

        SalesReceiptTransaction srt = SalesReceiptTransaction.builder()
                .empId(empId)
                .date(date)
                .amount(amount)
                .build();
        srt.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        Assertions.assertNotNull(cc);

        SalesReceipt sr = cc.getSalesReceipt(date);
        Assertions.assertNotNull(sr);
        Assertions.assertEquals(amount, sr.getAmount());

    }

}
