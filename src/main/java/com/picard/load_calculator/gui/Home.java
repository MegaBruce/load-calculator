package com.picard.load_calculator.gui;

import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.controller.FosterController;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Foster;
import com.picard.load_calculator.model.TrainningState;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class Home extends JFrame {
    public Home(
            ActivityController activityController,
            FosterController fosterController
    ) {
        super("Calculateur de charge");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(getClass().getSimpleName());
        setSize(800, 600);

        Container cp = this.getContentPane();

        SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {

                   Foster foster = fosterController.getTrainningState(LocalDate.now());
                   TrainningState trainningState = fosterController.calculateTrainningState(foster);

                   TrainningStateBanner trainningStateBanner = new TrainningStateBanner(
                           foster, trainningState
                   );
                   cp.add(trainningStateBanner.getRootPane(), BorderLayout.NORTH);

                   JTable table = new JTable();
                   List<Activity> activityList = activityController.getAllActivities();

                   String[] columnNames = {"Nom", "Date", "Charge"};
                   DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                   for (Activity activity : activityList) {
                       String name = activity.getName();
                       LocalDate date = activity.getDate();
                       int load = activity.getLoad();
                       model.addRow(new Object[]{name, date, load});
                   }
                   new GridLayout(3, 4);
                   table.setModel(model);
                   cp.add(new JScrollPane(table));
               }
           });

        JButton addActivityButton = new JButton("Ajouter une activité");
        cp.add(addActivityButton, BorderLayout.SOUTH);

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
                        dialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                cp.repaint();
                                cp.revalidate();
                            }
                        });
                    }
                });
            }
        });

        cp.revalidate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
