package com.w1838836;

import java.io.Serializable;

public abstract class Driver implements Serializable {
    protected String mName;
    protected String mLocation;
    protected String mTeam;
    protected int mRacesWon;

    /**
     * Default constructor.
     */
    public Driver() {

    }

    /**
     * Constructor.
     *
     * @param name     The name of the driver.
     * @param location The location of the driver,
     * @param team     The team of the driver.
     */
    public Driver(String name, String location, String team) {
        mName = name;
        mLocation = location;
        mTeam = team;
    }

    /**
     * Get the name of the driver.
     *
     * @return The name of the driver.
     */
    public String getName() {
        return mName;
    }

    /**
     * Set the name of the driver.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Get the location of the driver.
     *
     * @return The location.
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Set the location of the driver.
     *
     * @param location The location of the driver.
     */
    public void setLocation(String location) {
        mLocation = location;
    }

    /**
     * Get the team the driver played to.
     *
     * @return The team name.
     */
    public String getTeam() {
        return mTeam;
    }

    /**
     * Set the team the driver played to.
     *
     * @param team The team name to set.
     */
    public void setTeam(String team) {
        mTeam = team;
    }

    /**
     * Get the number of races the driver won.
     *
     * @return The number of races won.
     */
    public int getRacesWon() {
        return mRacesWon;
    }

    /**
     * Set the number of races the driver won.
     *
     * @param number The number of races to set.
     */
    public void setRacesWon(int number) {
        mRacesWon = number;
    }

    /**
     * Increment the races won counter by one.
     */
    public void incrementRacesWon() {
        mRacesWon++;
    }
}
