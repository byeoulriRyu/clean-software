package org.example.schedule;

import java.util.Date;

public class BiweeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date payDate) {
        return false;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return null;
    }
}
