package com.picard.load_calculator.controller;

import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Foster;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface FosterController {
    Foster getTrainningState(LocalDate date);
}
