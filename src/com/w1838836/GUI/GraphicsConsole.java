package com.w1838836.GUI;

import com.w1838836.Formula1ChampionshipManager;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GraphicsConsole {
    private final Formula1ChampionshipManager mManager = new Formula1ChampionshipManager();
    private final DriverStatisticsTable mDriverStatisticsTable = new DriverStatisticsTable();

    /**
     * Default constructor.
     */
    public GraphicsConsole() {
        Window window = new Window();
        UILayout layout = new UILayout(window.getContentPane());
        window.getContentPane().setLayout(layout);

        RandomButton randomButton = new RandomButton();
        ProbabilisticButton probabilisticButton = new ProbabilisticButton();
        RaceDataButton raceDataButton = new RaceDataButton();

        // Acts as columns.
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable.getTableHeader())
                                .addComponent(mDriverStatisticsTable))
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(randomButton, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width)
                                .addComponent(probabilisticButton)
                                .addComponent(raceDataButton, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width, probabilisticButton.getMinimumSize().width))
        );

        // Acts as rows.
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mDriverStatisticsTable.getTableHeader()))
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(randomButton)
                                        .addComponent(probabilisticButton)
                                        .addComponent(raceDataButton)))
        );

        // Add the action listeners as lambdas.
        randomButton.addActionListener((ActionEvent e) -> {
            mManager.addRace(false);
            mDriverStatisticsTable.setRows(mManager.toArray(true));
        });

        probabilisticButton.addActionListener((ActionEvent e) -> {
            mManager.addRaceProbabilistically();
            mDriverStatisticsTable.setRows(mManager.toArray(true));
        });

        raceDataButton.addActionListener((ActionEvent e) -> {
            RaceTable table = new RaceTable(mManager.raceToArray());

            Window raceInfoWindow = new Window("Race data");
            raceInfoWindow.setLayout(new BorderLayout());
            raceInfoWindow.add(table.getTableHeader(), BorderLayout.NORTH);
            raceInfoWindow.add(table, BorderLayout.CENTER);
            raceInfoWindow.setSize(table.getPreferredSize());
            raceInfoWindow.setVisible(true);
        });

        // Load the serialized data if possible.
        mManager.loadData(false);

        // Setup tables.
        mDriverStatisticsTable.setRows(mManager.toArray(true));

        window.setSize(1280, 720);
        window.setVisible(true);
    }

    /**
     * This function runs the command line inputs.
     */
    public void run() {
        boolean bShouldRun = true;
        while (bShouldRun) {
            Formula1ChampionshipManager.showMenu();
            Integer command = Formula1ChampionshipManager.getCommand();

            switch (command) {
                case 1 -> mManager.createNewDriver();
                case 2 -> mManager.deleteDriver();
                case 3 -> mManager.changeDriver();
                case 4 -> mManager.displayStatistics();
                case 5 -> mManager.displayDriverTable();
                case 6 -> mManager.addRace(true);
                case 7 -> mManager.saveData(true);
                case 8 -> mManager.loadData(true);
                case 9 -> bShouldRun = false;
            }

            mDriverStatisticsTable.setRows(mManager.toArray(true));
        }
    }

    /**
     * Clean up the end resources.
     */
    public void cleanup() {
        System.out.println("Exiting the application..");
        mManager.saveData(false);
        System.out.println("Thank you!");
    }
}
