package com.w1838836;

import java.io.Serializable;

public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {
    public static final int FIRST_POSITION = 0;
    public static final int SECOND_POSITION = 0;
    public static final int THIRD_POSITION = 0;

    private int[] mPositionsWon = new int[3];
    private int mPoints = 0;
    private int mNumberOfRaces = 0;

    /**
     * Default constructor.
     */
    public Formula1Driver() {
    }

    /**
     * Constructor.
     *
     * @param name     The name of the driver.
     * @param location The location of the driver.
     * @param team     The team of the driver.
     */
    public Formula1Driver(String name, String location, String team) {
        super(name, location, team);
    }

    /**
     * Get the positions array which holds the number of positions won.
     *
     * @return The positions won array.
     */
    public int[] getPositionsWon() {
        return mPositionsWon;
    }

    /**
     * Set the number of wins in a given position.
     *
     * @param position     The position won.
     * @param numberOfWins The number of wins.
     */
    public void setPosition(int position, int numberOfWins) {
        mPositionsWon[position] = numberOfWins;
    }

    /**
     * Increment the number of wins in a given position.
     *
     * @param position The position won.
     */
    public void incrementPositionWin(int position) {
        mPositionsWon[position]++;

        if (position == 0)
            incrementRacesWon();
    }

    /**
     * Get the number of points the driver has won.
     *
     * @return The number of points.
     */
    public int getPoints() {
        return mPoints;
    }

    /**
     * Set the number of points won.
     *
     * @param mPoints The number of points to set.
     */
    public void setPoints(int mPoints) {
        this.mPoints = mPoints;
    }

    /**
     * Update the points won by the driver using the position won,
     *
     * @param position The position won.
     */
    public void updatePoints(int position) {
        switch (position) {
            case 1 -> mPoints += 25;
            case 2 -> mPoints += 18;
            case 3 -> mPoints += 15;
            case 4 -> mPoints += 12;
            case 5 -> mPoints += 10;
            case 6 -> mPoints += 8;
            case 7 -> mPoints += 6;
            case 8 -> mPoints += 4;
            case 9 -> mPoints += 2;
            case 10 -> mPoints += 1;
        }
    }

    /**
     * Get the number of races won by the driver.
     *
     * @return The number of races won.
     */
    public int getNumberOfRaces() {
        return mNumberOfRaces;
    }

    /**
     * Set the number of races won by the driver.
     *
     * @param mNumberOfRaces The number of races won.
     */
    public void setNumberOfRaces(int mNumberOfRaces) {
        this.mNumberOfRaces = mNumberOfRaces;
    }

    /**
     * Increment the number of races the driver has run.
     */
    public void incrementNumberOfRaces() {
        mNumberOfRaces++;
    }

    /**
     * Overload of the Comparable<> interface's compare to function.
     *
     * @param formula1Driver The other formula 1 driver object to compare with.
     * @return The comparison. If this is greater than the other, the return is grater than 0. If it's less than the other
     * the return is less than 0. If the two are equal, the return is 0.
     */
    @Override
    public int compareTo(Formula1Driver formula1Driver) {
        if (mPoints > formula1Driver.mPoints)
            return 1;
        else if (mPoints < formula1Driver.mPoints)
            return -1;
        else if (mPositionsWon[FIRST_POSITION] > formula1Driver.mPositionsWon[FIRST_POSITION])
            return 1;
        else if (mPositionsWon[FIRST_POSITION] < formula1Driver.mPositionsWon[FIRST_POSITION])
            return -1;

        return 0;
    }
}
