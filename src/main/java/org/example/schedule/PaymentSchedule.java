package org.example.schedule;

import java.util.Date;

public interface PaymentSchedule {
    boolean isPayDate(Date payDate);

    Date getPayPeriodStartDate(Date payPeriodEndDate);
}
