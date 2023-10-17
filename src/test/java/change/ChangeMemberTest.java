package change;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.affiliation.Affiliation;
import org.example.affiliation.UnionAffiliation;
import org.example.entity.Employee;
import org.example.transaction.change.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ChangeMemberTest {

    private static final int empId = 2;
    private static final int memberId = 7734;


    @BeforeAll
    public static void before() throws Exception {
        PayrollDatabase.clear();

        String name = "Bill";
        String address = "Home";
        double hourlyRate = 15.25;

        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, address, hourlyRate);
        t.execute();
    }

    @Test
    @Order(1)
    public void testChangeMemberTransaction() throws Exception {

        double dues = 99.25;

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, dues);
        cmt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        Affiliation af = e.getAffiliation();
        Assertions.assertNotNull(af);

        UnionAffiliation uf = (UnionAffiliation) af;
        Assertions.assertNotNull(uf);
        Assertions.assertEquals(dues, uf.getDues());

        Employee member = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertEquals(e, member);
    }


}
