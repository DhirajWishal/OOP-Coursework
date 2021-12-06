package com.w1838836.GUI;

import com.w1838836.ChampionshipFactory;
import com.w1838836.ChampionshipManager;
import com.w1838836.Formula1ChampionshipManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsConsole {
    private ChampionshipManager mManager;
    private DriverStatisticsTable mDriverStatisticsTable = new DriverStatisticsTable();
    private Window mWindow = new Window();

    /**
     * Default constructor.
     */
    public GraphicsConsole() {
        // Create the manager.
        mManager = ChampionshipFactory.createManager("Formula 1");
        assert mManager != null;

        // Set up the UI.
        setupUIComponents();
    }

    /**
     * Set up all the UI components.
     */
    private void setupUIComponents() {
        // Create the window and layout.
        UILayout layout = new UILayout(mWindow.getContentPane());
        mWindow.getContentPane().setLayout(layout);

        // Create the buttons.
        RandomButton randomButton = new RandomButton();
        ProbabilisticButton probabilisticButton = new ProbabilisticButton();
        RaceDataButton raceDataButton = new RaceDataButton();
        SearchButton searchButton = new SearchButton();

        // Create the search results table.
        SearchResultsTable searchResultsTable = new SearchResultsTable(mWindow);

        // Create the text fields.
        SearchField searchField = new SearchField();
        SearchLabel searchText = new SearchLabel();
        DriverNameLabel driverName = new DriverNameLabel();

        int searchTextWidth = searchText.getPreferredSize().width;
        int probabilisticButtonWidth = probabilisticButton.getMinimumSize().width;
        int searchButtonHeight = searchButton.getPreferredSize().height;

        // Acts as columns.
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable.getTableHeader())
                                .addComponent(mDriverStatisticsTable)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchText, searchTextWidth, searchTextWidth, searchTextWidth)
                                        .addComponent(searchField)
                                )
                                .addComponent(driverName)
                                .addComponent(searchResultsTable.getTableHeader())
                                .addComponent(searchResultsTable)
                        )
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(randomButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                                .addComponent(probabilisticButton)
                                .addComponent(raceDataButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                                .addComponent(searchButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                        )
        );

        // Acts as rows.
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mDriverStatisticsTable.getTableHeader())
                        )
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(randomButton)
                                        .addComponent(probabilisticButton)
                                        .addComponent(raceDataButton)
                                )
                        )
                        .addGroup(layout.createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(searchText, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                                .addComponent(searchField, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                                .addComponent(searchButton, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                        )
                        .addComponent(driverName)
                        .addComponent(searchResultsTable.getTableHeader())
                        .addComponent(searchResultsTable)
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

            Window raceInfoWindow = new Window("Race Data");
            raceInfoWindow.setLayout(new BorderLayout());
            raceInfoWindow.add(table.getTableHeader(), BorderLayout.NORTH);
            raceInfoWindow.add(table, BorderLayout.CENTER);
            raceInfoWindow.setSize(table.getPreferredSize());
            raceInfoWindow.setVisible(true);
        });

        searchButton.addActionListener((ActionEvent e) -> {
            String textField = searchField.getText();

            if (textField.length() > 0) {
                if (searchResultsTable.searchAndDisplay(searchField.getText(), mManager.getRaces()))
                    driverName.setText("Search results for: " + textField);
                else
                    driverName.clear();
            } else {
                driverName.clear();
                searchResultsTable.clear();
            }

            searchField.clear();
        });

        // Load the serialized data if possible.
        mManager.loadData(false);

        // Setup tables.
        mDriverStatisticsTable.setRows(mManager.toArray(true));

        mWindow.setSize(1280, 720);
        mWindow.setVisible(true);

        // Add the window close even listener.
        mWindow.addWindowListener(new WindowAdapter() {
            /**
             * Window closing event listener.
             * @param windowEvent The window event.
             */
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(mWindow,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    cleanup();
                    System.exit(0);
                }
            }
        });
    }

    /**
     * This function runs the command line inputs.
     */
    public void run() {
        boolean bShouldRun = true;
        while (bShouldRun) {
            mManager.showMenu();
            int command = mManager.getCommand();

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
        mWindow.close();
        System.out.println("Thank you!");
    }
}
