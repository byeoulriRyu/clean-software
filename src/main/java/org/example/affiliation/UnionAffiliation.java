package org.example.affiliation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.ServiceCharge;
import org.example.transaction.pay.PayCheck;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class UnionAffiliation implements Affiliation {
    private int memberId;
    private double dues;

    @Builder.Default
    private Map<Date, ServiceCharge> serviceChargeMap = new LinkedHashMap<>();

    public ServiceCharge getServiceCharge(Date date) {
        return serviceChargeMap.get(date);
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        serviceChargeMap.put(serviceCharge.getDate(), serviceCharge);
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
