package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;

import javax.swing.*;
import java.awt.*;

public class ActivityFormDialog extends JDialog {
    public ActivityFormDialog (
                Frame owner,
                ActivityController activityController
    ) {
        super(owner);
        setPreferredSize(new Dimension(600, 400));
        setTitle(getClass().getSimpleName());

        pack();
        setLocationRelativeTo(getParent());

        Container cp = this.getContentPane();
        JPanel activityForm = new ActivityForm(activityController).getRootPanel();

        cp.add(activityForm);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
