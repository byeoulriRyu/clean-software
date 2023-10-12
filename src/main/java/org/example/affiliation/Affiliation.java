package org.example.affiliation;

import org.example.transaction.pay.PayCheck;

public interface Affiliation {
    double calculateDeductions(PayCheck pc);
}
