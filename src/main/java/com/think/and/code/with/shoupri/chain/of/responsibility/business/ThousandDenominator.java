package com.think.and.code.with.shoupri.chain.of.responsibility.business;

import com.think.and.code.with.shoupri.chain.of.responsibility.enums.DenominatorIdentity;
import com.think.and.code.with.shoupri.chain.of.responsibility.enums.NoteCount;
import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class ThousandDenominator implements Denominator {
    Denominator nextDenominator;
    private final Map<NoteCount, Long> notes;
    private final TerminalDenominator terminalDenominator;

    @Override
    public String getDenominatorIdentity() {
        return DenominatorIdentity.THOUSAND_DENOMINATOR_IDENTITY.name();
    }

    @Override
    public void setNextDenominator(Denominator nextDenominator) {
        this.nextDenominator = nextDenominator;
    }

    @Override
    public MoneyDenomination dispense(Long amount, MoneyDenomination moneyDenomination) {
        int thousandNoteCount = Math.toIntExact(amount / 1000);
        Long availableNoteCount = notes.getOrDefault(NoteCount.THOUSAND, 0L);
        if (availableNoteCount > 0) {
            if (availableNoteCount >= thousandNoteCount) {
                notes.put(NoteCount.THOUSAND, availableNoteCount - thousandNoteCount);
                amount = amount - (thousandNoteCount * 1000L);
                moneyDenomination.setThousandNoteCount((long) thousandNoteCount);
            } else {
                amount = amount - (availableNoteCount * 1000);
                moneyDenomination.setThousandNoteCount(availableNoteCount);
                notes.put(NoteCount.THOUSAND, 0L);
            }
        }
        if (amount > 0 && nextDenominator != null) {
            return nextDenominator.dispense(amount, moneyDenomination);
        }
        return terminalDenominator.dispense(amount, moneyDenomination);
    }
}
