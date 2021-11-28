package com.w1838836;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Driver implements Serializable {
    protected String mName;
    protected String mLocation;
    protected String mTeam;
    protected int mRacesWon;
    protected HashMap<Integer, Integer> mRaceMap;

    /**
     * Default constructor.
     */
    public Driver() {
        mRaceMap = new HashMap<>();
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
        mRaceMap = new HashMap<>();
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

    /**
     * Set the race information.
     *
     * @param race     The race ID of the race the driver participated in.
     * @param position The position of the driver in the given race.
     */
    public void setRaceInfo(final Integer race, final int position) {
        mRaceMap.put(race, position);
    }

    /**
     * Get the race information.
     *
     * @param race The race ID to get the information from.
     * @return The position the driver won.
     */
    public int getRaceInfo(final Integer race) {
        return mRaceMap.get(race);
    }
}
