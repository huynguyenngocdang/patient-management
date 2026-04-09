package com.huynguyenngocdang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

import static com.huynguyenngocdang.constant.AppConstant.DEFAULT_TIME_ZONE;

@Configuration
public class ClockConfig {
    @Bean
    public ZoneId zoneId() {
        return DEFAULT_TIME_ZONE;
    }

    @Bean
    public Clock clock(ZoneId zoneId) {
        return Clock.system(zoneId);
    }
}
