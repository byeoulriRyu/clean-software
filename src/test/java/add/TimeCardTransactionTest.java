package add;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.TimeCard;
import org.example.transaction.add.TimeCardTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class TimeCardTransactionTest {

    @BeforeAll
    public static void before() {
        PayrollDatabase.clear();
    }

    @Test
    public void testTimeCardTransaction() throws Exception {
        int empId = 2;
        String name = "Bill";
        String address = "Home";
        double hourlyRate = 15.25;
        long date = 20011031;
        double hours = 8.0;

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, name, address, hourlyRate);
        addHourlyEmployee.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 31);
        Date payDate = calendar.getTime();

        TimeCardTransaction tct = TimeCardTransaction.builder()
                .date(payDate)
                .hours(hours)
                .empId(empId)
                .build();

        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        Assertions.assertNotNull(hc);

        TimeCard tc = hc.getTimeCard(date);
        Assertions.assertNotNull(tc);
        Assertions.assertEquals(hours, tc.getHours());

    }

}
