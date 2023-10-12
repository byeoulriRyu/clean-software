package org.example.entity;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SalesReceipt {

    private long date;
    private double amount;

}
