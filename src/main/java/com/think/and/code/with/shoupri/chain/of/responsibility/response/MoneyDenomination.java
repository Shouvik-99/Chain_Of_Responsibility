package com.think.and.code.with.shoupri.chain.of.responsibility.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoneyDenomination {
    private Long thousandNoteCount;
    private Long fiveHundredNoteCount;
    private Long twoHundredNoteCount;
    private String mesage;
}
