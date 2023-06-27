package com.picard.load_calculator.gui;

import lombok.Getter;

import javax.swing.*;

public class TrainningStateBanner {
    private JLabel loadLabel;
    private JLabel monotonyLabel;
    private JLabel strainLabel;
    private JLabel fitnessLabel;
    private JLabel acwrLabel;
    private JTextField loadField;
    private JTextField monotonyField;
    private JTextField strainField;
    private JTextField fitnessField;
    private JTextField acwrField;
    @Getter
    private JPanel rootPane;
}
