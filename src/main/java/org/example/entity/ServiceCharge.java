package org.example.entity;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ServiceCharge {

    private long date;
    private double amount;

}
