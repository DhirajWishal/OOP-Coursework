package com.w1838836;

public class Formula1Driver extends Driver {
    public static final int FIRST_POSITION = 0;
    public static final int SECOND_POSITION = 0;
    public static final int THIRD_POSITION = 0;

    private int[] mPositionsWon;
    private int mPoints = 0;
    private int mNumberOfRaces = 0;

    /**
     * Default constructor.
     */
    public Formula1Driver() {
        mPositionsWon = new int[3];
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
            case 1:
                mPoints += 25;
                break;
            case 2:
                mPoints += 18;
                break;
            case 3:
                mPoints += 15;
                break;
            case 4:
                mPoints += 12;
                break;
            case 5:
                mPoints += 10;
                break;
            case 6:
                mPoints += 8;
                break;
            case 7:
                mPoints += 6;
                break;
            case 8:
                mPoints += 4;
                break;
            case 9:
                mPoints += 2;
                break;
            case 10:
                mPoints += 1;
                break;
            default:
                break;
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
}
