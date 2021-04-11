package com.example.demo.users;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.users")
public class UserConfig {

    /**
     * Max users allowed to register
     */
    private Integer max = 5;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
