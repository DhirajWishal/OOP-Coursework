package com.w1838836;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private int mNumberOfCars;
    private int mNumberOfDrivers;

    /**
     * Get the number of cars.
     *
     * @return The number of cars.
     */
    public int getNumberOfCars() {
        return mNumberOfCars;
    }

    /**
     * Set the number of cars in the manager.
     *
     * @param numberOfCars The number of cars to set.
     */
    public void setNumberOfCars(int numberOfCars) {
        mNumberOfCars = numberOfCars;
    }

    /**
     * Get the number of drivers.
     *
     * @return The number of drivers.
     */
    public int getNumberOfDrivers() {
        return mNumberOfDrivers;
    }

    /**
     * Set the number of drivers in the manager.
     *
     * @param numberOfDrivers The number of drivers to set.
     */
    public void setNumberOfDrivers(int numberOfDrivers) {
        mNumberOfDrivers = numberOfDrivers;
    }
}
