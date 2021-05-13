package com.example.demo.cache;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Region {

    private String name;
    private String code;
    private boolean active;
}
