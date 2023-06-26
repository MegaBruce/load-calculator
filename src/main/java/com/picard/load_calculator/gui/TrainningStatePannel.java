package com.picard.load_calculator.gui;

import com.picard.load_calculator.model.Foster;

import javax.swing.*;

public class TrainningStatePannel {
    private JLabel labelMonotony;
    private JLabel labelLoad;
    private JLabel labelStrain;
    private JLabel labelFitness;
    private JLabel labelACWR;
    private Foster foster;
    private TrainningStatePannel trainningStatePannel;

    public TrainningStatePannel(Foster foster, TrainningStatePannel trainningStatePannel) {
        this.foster = foster;
        this.trainningStatePannel = trainningStatePannel;
    }


}
