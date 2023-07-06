package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.controller.FosterController;
import com.picard.load_calculator.model.Foster;
import com.picard.load_calculator.model.TrainningState;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

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
            Foster foster,
            TrainningState trainningState
    ) {
        DecimalFormat f = new DecimalFormat("#0.00");
        loadField.setText(String.valueOf(foster.getLoad()));
        monotonyField.setText(f.format(foster.getMonotony()));
        strainField.setText(String.valueOf(foster.getStrain()));
        fitnessField.setText(String.valueOf(foster.getFitness()));
        acwrField.setText(f.format(foster.getAcwr()));


        switch (trainningState){
            case OPTIMAL -> {
                messageLabel.setText("Entrainement optimal");
                rootPane.setBackground(new Color(93, 175, 106));
            }
            case TIRED -> {
                messageLabel.setText("État de fatigue");
                rootPane.setBackground(new Color(175, 142, 93));
            }
            case INJURY -> {
                messageLabel.setText("Attention ! Risque de blessure");
                rootPane.setBackground(new Color(164, 70, 70));
            }
            case RAS -> messageLabel.setText("RAS");
        }
    }
}
