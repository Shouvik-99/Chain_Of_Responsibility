package com.think.and.code.with.shoupri.chain.of.responsibility.config;

import com.think.and.code.with.shoupri.chain.of.responsibility.enums.NoteCount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommonConfig {

    @Bean
    public Map<NoteCount, Long> providesAvailableNotes() {
        Map<NoteCount, Long> availableNotes = new HashMap<>();
        availableNotes.put(NoteCount.THOUSAND, 10L);
        availableNotes.put(NoteCount.FIVE_HUNDRED, 10L);
        availableNotes.put(NoteCount.TWO_HUNDRED, 10L);
        return availableNotes;
    }
}
