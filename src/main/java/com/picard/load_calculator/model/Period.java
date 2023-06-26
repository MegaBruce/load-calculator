package com.picard.load_calculator.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Period {
    private Date startDate;
    private Date endDate;
}