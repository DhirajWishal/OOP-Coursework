package com.w1838836.GUI;

import com.w1838836.ChampionshipManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UILayout extends GroupLayout {
    private final RandomButton mRandomButton;
    private final ProbabilisticButton mProbabilisticButton;
    private final RaceDataButton mRaceDataButton;
    private final SearchButton mSearchButton;
    private final SearchResultsTable mSearchResultsTable;
    private final SearchField mSearchField;
    private final SearchLabel mSearchText;
    private final DriverNameLabel mDriverName;

    private final DriverStatisticsTable mDriverStatisticsTable = new DriverStatisticsTable();

    /**
     * Constructor.
     *
     * @param host    The host container.
     * @param window  The window object.
     * @param manager The championship manager object.
     */
    public UILayout(Container host, Window window, ChampionshipManager manager) {
        super(host);

        // Create the buttons.
        mRandomButton = new RandomButton();
        mProbabilisticButton = new ProbabilisticButton();
        mRaceDataButton = new RaceDataButton();
        mSearchButton = new SearchButton();

        // Create the search results table.
        mSearchResultsTable = new SearchResultsTable(window);

        // Create the text fields.
        mSearchField = new SearchField();
        mSearchText = new SearchLabel();
        mDriverName = new DriverNameLabel();

        int searchTextWidth = mSearchText.getPreferredSize().width;
        int probabilisticButtonWidth = mProbabilisticButton.getMinimumSize().width;
        int searchButtonHeight = mSearchButton.getPreferredSize().height;

        // Acts as columns.
        setHorizontalGroup(
                createSequentialGroup()
                        .addGroup(createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable.getTableHeader())
                                .addComponent(mDriverStatisticsTable.getPane())
                                .addGroup(createSequentialGroup()
                                        .addComponent(mSearchText, searchTextWidth, searchTextWidth, searchTextWidth)
                                        .addComponent(mSearchField)
                                )
                                .addComponent(mDriverName)
                                .addComponent(mSearchResultsTable.getTableHeader())
                                .addComponent(mSearchResultsTable.getPane())
                        )
                        .addGroup(createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mRandomButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                                .addComponent(mProbabilisticButton)
                                .addComponent(mRaceDataButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                                .addComponent(mSearchButton, probabilisticButtonWidth, probabilisticButtonWidth, probabilisticButtonWidth)
                        )
        );

        // Acts as rows.
        setVerticalGroup(
                createSequentialGroup()
                        .addGroup(createSequentialGroup()
                                .addComponent(mDriverStatisticsTable.getTableHeader())
                        )
                        .addGroup(createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mDriverStatisticsTable.getPane())
                                .addGroup(createSequentialGroup()
                                        .addComponent(mRandomButton)
                                        .addComponent(mProbabilisticButton)
                                        .addComponent(mRaceDataButton)
                                )
                        )
                        .addGroup(createParallelGroup(UILayout.Alignment.LEADING)
                                .addComponent(mSearchText, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                                .addComponent(mSearchField, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                                .addComponent(mSearchButton, searchButtonHeight, searchButtonHeight, searchButtonHeight)
                        )
                        .addComponent(mDriverName)
                        .addComponent(mSearchResultsTable.getTableHeader())
                        .addComponent(mSearchResultsTable.getPane())
        );

        // Set up the listeners.
        setupListeners(manager);

        setAutoCreateGaps(true);
        setAutoCreateContainerGaps(true);
    }

    /**
     * Set up all the action listeners.
     *
     * @param manager The manager object.
     */
    private void setupListeners(ChampionshipManager manager) {
        // Add the action listeners as lambdas.
        mRandomButton.addActionListener((ActionEvent e) -> {
            manager.addRace(false);
            mDriverStatisticsTable.setRows(manager.toArray(true));
        });

        mProbabilisticButton.addActionListener((ActionEvent e) -> {
            manager.addRaceProbabilistically();
            mDriverStatisticsTable.setRows(manager.toArray(true));
        });

        mRaceDataButton.addActionListener((ActionEvent e) -> {
            RaceTable table = new RaceTable(manager.raceToArray());

            Window raceInfoWindow = new Window("Race Data");
            raceInfoWindow.setLayout(new BorderLayout());
            raceInfoWindow.add(table.getTableHeader(), BorderLayout.NORTH);
            raceInfoWindow.add(table.getPane(), BorderLayout.CENTER);
            raceInfoWindow.setSize(1280, 720);
            raceInfoWindow.setVisible(true);
        });

        mSearchButton.addActionListener((ActionEvent e) -> {
            String textField = mSearchField.getText();

            if (textField.length() > 0) {
                if (mSearchResultsTable.searchAndDisplay(mSearchField.getText(), manager.getRaces()))
                    mDriverName.setText("Search results for: " + textField);
                else
                    mDriverName.clear();
            } else {
                mDriverName.clear();
                mSearchResultsTable.clear();
            }

            mSearchField.clear();
        });
    }

    /**
     * Get the driver statistics table from the layout.
     *
     * @return The driver statistics table.
     */
    public DriverStatisticsTable getDriverStatisticsTable() {
        return mDriverStatisticsTable;
    }
}
