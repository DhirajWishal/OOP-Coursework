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
    private Window mWindow = new Window();
    private UILayout mLayout;

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
        mLayout = new UILayout(mWindow.getContentPane(), mWindow, mManager);
        mWindow.getContentPane().setLayout(mLayout);

        // Load the serialized data if possible.
        mManager.loadData(false);

        // Setup tables.
        mLayout.getDriverStatisticsTable().setRows(mManager.toArray(true));

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
                cleanup();
                System.exit(0);
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

            mLayout.getDriverStatisticsTable().setRows(mManager.toArray(true));
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
