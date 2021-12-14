package com.w1838836;

import java.util.ArrayList;

public interface ChampionshipManager {
    /**
     * Show the championship menu.
     */
    public void showMenu();

    /**
     * Get a new command from the user.
     *
     * @return The selected command.
     */
    public int getCommand();

    /**
     * Create a new driver.
     */
    public void createNewDriver();

    /**
     * Display all the drivers.
     */
    public void displayDrivers();

    /**
     * Delete a driver from the championship.
     */
    public void deleteDriver();

    /**
     * Change a driver team.
     */
    public void changeDriver();

    /**
     * Display driver statistics.
     */
    public void displayStatistics();

    /**
     * Display the driver table.
     */
    public void displayDriverTable();

    /**
     * Add a new race.
     *
     * @param bStatus The status, if the command comes from the GUI or CMD.
     */
    public void addRace(boolean bStatus);

    /**
     * Add a new probabilistically won race.
     */
    public void addRaceProbabilistically();

    /**
     * Save data to a local file.
     *
     * @param bShouldWarn Whether to show the user if there was an error.
     */
    public void saveData(boolean bShouldWarn);

    /**
     * Load the application data to a local file.
     *
     * @param bShouldWarn Whether to show the user if there was an error.
     */
    public void loadData(boolean bShouldWarn);

    /**
     * Convert the data to a 2D array.
     *
     * @param bShouldSort Whether to sort the data.
     * @return The dumped array.
     */
    public String[][] toArray(boolean bShouldSort);

    /**
     * Get all the stored races.
     *
     * @return The races.
     */
    public ArrayList<Race> getRaces();

    /**
     * Covert the race data to a 2D array.
     *
     * @return The dumped array.
     */
    public String[][] raceToArray();
}
