package com.think.and.code.with.shoupri.chain.of.responsibility.controller;

import com.think.and.code.with.shoupri.chain.of.responsibility.business.Denominator;
import com.think.and.code.with.shoupri.chain.of.responsibility.request.WithdrawRequest;
import com.think.and.code.with.shoupri.chain.of.responsibility.response.MoneyDenomination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.think.and.code.with.shoupri.chain.of.responsibility.config.BusinessConfig.CHAIN_START_DENOMINATOR;

@RestController
@RequestMapping("/withdraw")
@RequiredArgsConstructor
public class MoneyWithDrawController {

    @Qualifier(CHAIN_START_DENOMINATOR)
    private final Denominator chainStartDenominator;

    @PostMapping(path = "/money")
    public ResponseEntity<MoneyDenomination> withdrawMoney(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(chainStartDenominator.dispense(request.getAmount(),
                MoneyDenomination.builder().build()));
    }
}
