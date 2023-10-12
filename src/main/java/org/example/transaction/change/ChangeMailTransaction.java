package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.transaction.Transaction;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ChangeMailTransaction implements Transaction {

    private String address;

}
