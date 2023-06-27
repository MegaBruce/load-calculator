package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
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
        super("Calculateur de charge");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(getClass().getSimpleName());
        setSize(800, 600);

        setLocationRelativeTo(null);

        Container cp = this.getContentPane();

        JButton addActivityButton = new JButton("Ajouter une activité");
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ActivityFormDialog dialog = new ActivityFormDialog(Home.this, activityController);
                        dialog.setModal(true);
                        dialog.setVisible(true);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    }
                });
            }
        });

        cp.add(addActivityButton, FlowLayout.LEFT);
        cp.revalidate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
