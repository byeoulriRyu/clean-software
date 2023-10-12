package org.example.affiliation;

import lombok.Builder;
import lombok.Getter;
import org.example.entity.ServiceCharge;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Builder
public class UnionAffiliation implements Affiliation {
    private double amount;

    @Builder.Default
    private Map<Long, ServiceCharge> serviceChargeMap = new LinkedHashMap<>();

    public ServiceCharge getServiceCharge(long date) {
        return serviceChargeMap.get(date);
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        serviceChargeMap.put(serviceCharge.getDate(), serviceCharge);
    }
}
