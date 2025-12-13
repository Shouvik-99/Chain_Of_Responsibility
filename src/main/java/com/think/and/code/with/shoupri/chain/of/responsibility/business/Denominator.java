package com.think.and.code.with.shoupri.chain.of.responsibility.business;

import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;

public interface Denominator {
    String getDenominatorIdentity();
    void setNextDenominator(Denominator nextDenominator);
    MoneyDenomination dispense(Long amount, MoneyDenomination moneyDenomination);
}
