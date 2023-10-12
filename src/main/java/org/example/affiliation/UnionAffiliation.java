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
public class UnionAffiliation implements Affiliation {
    private int memberId;
    private double dues;

    @Builder.Default
    private Map<Long, ServiceCharge> serviceChargeMap = new LinkedHashMap<>();

    public ServiceCharge getServiceCharge(long date) {
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
