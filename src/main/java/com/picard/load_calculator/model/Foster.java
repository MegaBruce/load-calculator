package com.picard.load_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foster {
    private int load;
    private double monotony;
    private int strain;
    private int fitness;
    private double acwr;
}
