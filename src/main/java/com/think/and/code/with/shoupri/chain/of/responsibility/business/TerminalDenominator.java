package com.think.and.code.with.shoupri.chain.of.responsibility.business;

import com.think.and.code.with.shoupri.chain.of.responsibility.enums.DenominatorIdentity;
import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;
import org.springframework.stereotype.Component;

@Component
public class TerminalDenominator implements Denominator {

    @Override
    public String getDenominatorIdentity() {
        return DenominatorIdentity.TERMINAL_DENOMINATOR_IDENTITY.name();
    }

    @Override
    public void setNextDenominator(Denominator nextDenominator) {
        // Terminal denominator does not have a next denominator
    }

    @Override
    public MoneyDenomination dispense(Long amount, MoneyDenomination moneyDenomination) {
        if (amount > 0) {
            moneyDenomination.setMesage("Remaining amount " + amount + " cannot be dispensed due to insufficient denominations.");
        } else {
            moneyDenomination.setMesage("Dispensing completed successfully.");
        }
        return moneyDenomination;
    }
}
