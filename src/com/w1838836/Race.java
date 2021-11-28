package com.w1838836;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Race implements Comparable<Race>, Serializable {
    public enum RaceStatus {
        INSERTED,
        GENERATED
    }

    private ArrayList<Driver> mDrivers;
    private RaceStatus mStatus;
    private Date mDate;

    /**
     * Constructor.
     *
     * @param drivers The drivers participated in the race.
     * @param status  The status of the race.
     * @param date    The date in which the race took place.
     */
    public Race(ArrayList<Driver> drivers, RaceStatus status, Date date) {
        mDrivers = drivers;
        mStatus = status;
        mDate = date;
    }

    /**
     * Get the stored drivers.
     *
     * @return The stored drivers.
     */
    public ArrayList<Driver> getDrivers() {
        return mDrivers;
    }

    /**
     * Set the drivers that participated in a race.
     *
     * @param mDrivers The drivers to set.
     */
    public void setDrivers(ArrayList<Driver> mDrivers) {
        this.mDrivers = mDrivers;
    }

    /**
     * Get the race status.
     *
     * @return The status.
     */
    public RaceStatus getStatus() {
        return mStatus;
    }

    /**
     * Set the race status.
     *
     * @param mStatus The status to set.
     */
    public void setStatus(RaceStatus mStatus) {
        this.mStatus = mStatus;
    }

    /**
     * Get the date the race took place in.
     *
     * @return The date.
     */
    public Date getDate() {
        return mDate;
    }

    /**
     * Set the date which the race took place int.
     *
     * @param mDate The date to set.
     */
    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    /**
     * Compare to function override.
     *
     * @param race The other race to compare with.
     * @return The compared result.
     */
    @Override
    public int compareTo(Race race) {
        return mDate.compareTo(race.getDate());
    }
}
