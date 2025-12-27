package com.think.and.code.with.shoupri.chain.of.responsibility.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDenomination {
    private Long thousandNoteCount;
    private Long fiveHundredNoteCount;
    private Long twoHundredNoteCount;
    private String mesage;
}
