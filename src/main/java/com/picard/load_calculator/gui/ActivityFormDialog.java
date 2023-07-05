package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;

import javax.swing.*;
import java.awt.*;

public class ActivityFormDialog extends JDialog {
    private Frame owner;
    public ActivityFormDialog (
            Frame owner,
            ActivityController activityController
    ) {
        super(owner);
        this.owner = owner;
        setTitle(getClass().getSimpleName());
        setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getParent());

        Container cp = this.getContentPane();
        JPanel activityForm = new ActivityForm(this, activityController).getRootPanel();

        cp.add(activityForm);
        cp.revalidate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
