package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.model.Activity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ActivityForm {
    @Getter
    private JPanel rootPanel;
    private JTextField fieldName;
    private JLabel labelDuration;
    private JTextField fieldDuration;
    private JLabel LabelRpe;
    private JSlider fieldRpe;
    private JLabel labelDate;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel labelName;
    private JLabel rpeReadOnlyValue;
    private JFormattedTextField fieldDate;

    private ActivityController activityController;
    private Window owner;

    public ActivityForm(
            Window owner,
            ActivityController activityController
    ) {
        this.activityController = activityController;
        this.owner = owner;
        saveButton.addActionListener(new SaveActivityButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        fieldRpe.addChangeListener(new RpeChangeListener());
        rootPanel.addComponentListener(new ComponentAdapter() {
        });
    }

    private void createUIComponents() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        fieldDate = new JFormattedTextField(df);
    }

    class SaveActivityButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String textNameValue = fieldName.getText();
                int rpe = fieldRpe.getValue();
                int duration = Integer.parseInt(fieldDuration.getText());
                Date date = (Date) fieldDate.getValue();
                date.setHours(date.getHours() + 2);

                log.info("I want to save an activity called : {}", textNameValue);
                log.info("With RPE : {}", rpe);
                log.info("And duration : {}", duration);
                log.info("That I did on the {}", date);
                Activity activity = new Activity(
                        textNameValue,
                        duration,
                        rpe,
                        date
                );
                activityController.save(activity);
                owner.dispose();
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid data");
            }
        }
    }

    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            log.info("Quit without saving");
            owner.dispose();
        }
    }

    class RpeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            rpeReadOnlyValue.setText(String.valueOf(fieldRpe.getValue()));
        }
    };
}
