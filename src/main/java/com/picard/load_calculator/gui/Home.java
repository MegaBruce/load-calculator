package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class Home extends JFrame {
    public Home(
            ActivityController activityController
    ) {
        super();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(getClass().getSimpleName());
        setSize(800, 600);

        JPanel panelTrainningState = new JPanel();
        panelTrainningState.setBackground(Color.green);

        ActivityForm activityForm = new ActivityForm(activityController);
        TrainningStateBanner trainningStateBanner = new TrainningStateBanner();

        add(trainningStateBanner.getRootPane(), BorderLayout.NORTH);
        add(activityForm.getRootPanel(), BorderLayout.CENTER);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
