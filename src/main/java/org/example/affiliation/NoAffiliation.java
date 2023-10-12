package org.example.affiliation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.ServiceCharge;
import org.example.transaction.pay.PayCheck;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class NoAffiliation implements Affiliation {

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
