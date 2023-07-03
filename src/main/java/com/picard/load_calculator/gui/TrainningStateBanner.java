package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.controller.FosterController;
import com.picard.load_calculator.model.Foster;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class TrainningStateBanner {
    private JLabel strainLabel;
    private JTextField strainField;
    @Getter
    private JPanel rootPane;
    private JLabel fitnessLabel;
    private JTextField fitnessField;
    private JLabel messageLabel;
    private JLabel loadLabel;
    private JTextField loadField;
    private JLabel acwrLabel;
    private JTextField acwrField;
    private JTextField monotonyField;

    public TrainningStateBanner(
            Foster foster
    ) {
        loadField.setText(String.valueOf(foster.getLoad()));
        monotonyField.setText(String.valueOf(foster.getMonotony()));
        strainField.setText(String.valueOf(foster.getStrain()));
        fitnessField.setText(String.valueOf(foster.getFitness()));
        acwrField.setText(String.valueOf(foster.getAcwr()));

        switch (foster.getTrainningState()){
            case OPTIMAL -> messageLabel.setText("Entrainement optimal");
            case TIRED -> messageLabel.setText("État de fatigue");
            case INJURY -> messageLabel.setText("Attention ! Risque de blessure");
            case RAS -> messageLabel.setText("RAS");
        }
    }
}
