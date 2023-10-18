package org.example.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Getter
@Builder
public class ServiceCharge {

    private Date date;
    private double amount;

}
