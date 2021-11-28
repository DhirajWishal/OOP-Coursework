package com.w1838836;

import com.w1838836.GUI.*;
import com.w1838836.GUI.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        Window window = new Window();
        DriverStatisticsTable table = new DriverStatisticsTable();
        RandomButton randomButton = new RandomButton();
        ProbabilisticButton probabilisticButton = new ProbabilisticButton();

        UILayout layout = new UILayout(window.getContentPane());
        window.getContentPane().setLayout(layout);

        // Acts as columns.
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(table.getTableHeader())
                                .addComponent(table))
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(randomButton, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width)
                                .addComponent(probabilisticButton))
        );

        // Acts as rows.
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(table.getTableHeader()))
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(table)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(randomButton)
                                        .addComponent(probabilisticButton)))
        );

        // Add the action listener as a lambda.
        randomButton.addActionListener((ActionEvent e) -> {
            manager.addRace();
            table.setRows(manager.toArray(true));
        });

        probabilisticButton.addActionListener((ActionEvent e) -> {
            manager.addRace();
            table.setRows(manager.toArray(true));
        });

        // Load the serialized data if possible.
        manager.loadData(false);

        table.setRows(manager.toArray(true));
        window.setSize(1280, 720);
        window.setVisible(true);

        boolean bShouldRun = true;
        while (bShouldRun) {
            Formula1ChampionshipManager.showMenu();
            Integer command = Formula1ChampionshipManager.getCommand();

            switch (command) {
                case 1 -> manager.createNewDriver();
                case 2 -> manager.deleteDriver();
                case 3 -> manager.changeDriver();
                case 4 -> manager.displayStatistics();
                case 5 -> manager.displayDriverTable();
                case 6 -> manager.addRace();
                case 7 -> manager.saveData(true);
                case 8 -> manager.loadData(true);
                case 9 -> bShouldRun = false;
            }

            table.setRows(manager.toArray(true));
        }

        System.out.println("Exiting the application..");
        manager.saveData(false);
        System.out.println("Thank you!");
    }
}
