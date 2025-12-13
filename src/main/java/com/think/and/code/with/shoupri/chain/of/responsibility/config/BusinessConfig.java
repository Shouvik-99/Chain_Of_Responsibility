package com.think.and.code.with.shoupri.chain.of.responsibility.config;

import com.think.and.code.with.shoupri.chain.of.responsibility.business.Denominator;
import com.think.and.code.with.shoupri.chain.of.responsibility.enums.DenominatorIdentity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class BusinessConfig {

    public static final String CHAIN_START_DENOMINATOR = "chainStartDenominator";
    private final List<Denominator> denominators;

    @Bean(CHAIN_START_DENOMINATOR)
    public Denominator providesDenominatorChain() {
        Map<String, Denominator> denominatorMap = new HashMap<>();
        denominators.forEach(denominator ->
                denominatorMap.put(denominator.getDenominatorIdentity(), denominator));
        List<DenominatorIdentity> denominatorIdentityList = List.of(DenominatorIdentity.values());
        for (int i = 0; i < denominatorIdentityList.size() - 1; i++) {
            Denominator currentDenominator = denominatorMap.get(denominatorIdentityList.get(i).name());
            Denominator nextDenominator = denominatorMap.get(denominatorIdentityList.get(i + 1).name());
            currentDenominator.setNextDenominator(nextDenominator);
        }
        return denominatorMap.get(denominatorIdentityList.get(0).name());
    }
}
