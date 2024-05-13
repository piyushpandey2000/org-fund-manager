package com.assignment.temeliobackend;

import com.assignment.temeliobackend.exception.InsufficientFundsException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Foundation {
    private String name;
    private int currFund;
    private int fundTransferred;
    private int numTransfers;

    @JsonIgnore
    public static final Foundation instance =
            new Foundation("Sample Foundation", 1000000, 0, 0);

    public void transferFund(int fundAmount) {
        if (currFund < fundAmount) {
            throw new InsufficientFundsException(String.format("Funds less than %d", fundAmount));
        }
        numTransfers++;
        fundTransferred += fundAmount;
        currFund -= fundAmount;
    }
}
