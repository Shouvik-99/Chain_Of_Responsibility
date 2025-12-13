package com.think.and.code.with.shoupri.chain.of.responsibility.business;

import com.think.and.code.with.shoupri.chain.of.responsibility.enums.DenominatorIdentity;
import com.think.and.code.with.shoupri.chain.of.responsibility.enums.NoteCount;
import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class FiveHundredDenominator implements Denominator {
    
    Denominator nextDenominator;
    private final Map<NoteCount, Long> notes;
    private final TerminalDenominator terminalDenominator;

    @Override
    public String getDenominatorIdentity() {
        return DenominatorIdentity.FIVE_HUNDRED_DENOMINATOR_IDENTITY.name();
    }

    @Override
    public void setNextDenominator(Denominator nextDenominator) {
        this.nextDenominator = nextDenominator;
    }

    @Override
    public MoneyDenomination dispense(Long amount, MoneyDenomination moneyDenomination) {
        int fiveHundredNoteCount  = Math.toIntExact(amount / 500);
        Long availableNoteCount = notes.getOrDefault(NoteCount.FIVE_HUNDRED, 0L);
        if (availableNoteCount > 0) {
            if (availableNoteCount >= fiveHundredNoteCount ) {
                notes.put(NoteCount.FIVE_HUNDRED, availableNoteCount - fiveHundredNoteCount );
                amount = amount - (fiveHundredNoteCount  * 500L);
                moneyDenomination.setFiveHundredNoteCount((long) fiveHundredNoteCount );
            } else {
                amount = amount - (availableNoteCount * 500);
                moneyDenomination.setFiveHundredNoteCount (availableNoteCount);
                notes.put(NoteCount.FIVE_HUNDRED, 0L);
            }
        }
        if (amount > 0 && nextDenominator != null) {
            return nextDenominator.dispense(amount, moneyDenomination);
        }
    return terminalDenominator.dispense(amount, moneyDenomination);
    }
}
