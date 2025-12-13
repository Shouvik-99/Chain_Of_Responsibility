package com.think.and.code.with.shoupri.chain.of.responsibility.business;

import com.think.and.code.with.shoupri.chain.of.responsibility.enums.DenominatorIdentity;
import com.think.and.code.with.shoupri.chain.of.responsibility.enums.NoteCount;
import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class TwoHundredDenominator implements Denominator{
    Denominator nextDenominator;
    private final Map<NoteCount, Long> notes;
    private final TerminalDenominator terminalDenominator;

    @Override
    public String getDenominatorIdentity() {
        return DenominatorIdentity.TWO_HUNDRED_DENOMINATOR_IDENTITY.name();
    }

    @Override
    public void setNextDenominator(Denominator nextDenominator) {
        this.nextDenominator = nextDenominator;
    }

    @Override
    public MoneyDenomination dispense(Long amount, MoneyDenomination moneyDenomination) {
        int twoHundredNoteCount  = Math.toIntExact(amount / 200);
        Long availableNoteCount = notes.getOrDefault(NoteCount.TWO_HUNDRED, 0L);
        if (availableNoteCount > 0) {
            if (availableNoteCount >= twoHundredNoteCount ) {
                notes.put(NoteCount.TWO_HUNDRED, availableNoteCount - twoHundredNoteCount );
                amount = amount - (twoHundredNoteCount  * 200L);
                moneyDenomination.setTwoHundredNoteCount ((long) twoHundredNoteCount );
            } else {
                amount = amount - (availableNoteCount * 200);
                moneyDenomination.setTwoHundredNoteCount (availableNoteCount);
                notes.put(NoteCount.TWO_HUNDRED, 0L);
            }
        }
        if (amount > 0 && nextDenominator != null) {
            return nextDenominator.dispense(amount, moneyDenomination);
        }
        return terminalDenominator.dispense(amount, moneyDenomination);
    }
}
