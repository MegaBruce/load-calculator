package com.picard.load_calculator.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CalculateSD2 {

    public static int doStandardDerivation(List<Integer> arr) {
        int n = arr.size();
        double sum = 0.0;
        double standardDeviation = 0.0;
        double mean = 0.0;
        double res = 0.0;
        double sq = 0.0;

        sum = arr.stream().reduce(0, (a, b) -> a+b);
        mean = sum / (n);

        for (Integer item: arr) {
            standardDeviation += Math.pow((item - mean), 2);
        };

        sq = standardDeviation / n;
        res = Math.sqrt(sq);
        return (int) Math.round(res);
    }
}
