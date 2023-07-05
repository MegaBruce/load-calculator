package com.picard.load_calculator.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
}