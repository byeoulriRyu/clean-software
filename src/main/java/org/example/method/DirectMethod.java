package org.example.method;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DirectMethod implements PaymentMethod {

    private String bank;
    private String account;
}
